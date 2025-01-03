#!/bin/bash

sudo apt-get update
sudo apt-get -y install wget systemctl
wget https://github.com/open-telemetry/opentelemetry-collector-releases/releases/download/v0.105.0/otelcol-contrib_0.105.0_linux_amd64.deb
sudo dpkg -i otelcol-contrib_0.105.0_linux_amd64.deb

#sudo yum update
#sudo yum -y install wget systemctl
#wget https://github.com/open-telemetry/opentelemetry-collector-releases/releases/download/v0.105.0/otelcol-contrib_0.105.0_linux_amd64.rpm
#sudo rpm -ivh otelcol-contrib_0.105.0_linux_amd64.rpm

cat <<EOF | tee /etc/otelcol-contrib/config.yaml
extensions:
  health_check:
  pprof:
    endpoint: 0.0.0.0:1777
  zpages:
    endpoint: 0.0.0.0:55679

receivers:
  otlp:
    protocols:
      grpc:
        endpoint: 0.0.0.0:4317
      http:
        endpoint: 0.0.0.0:4318

  hostmetrics:
    collection_interval: 30s
    scrapers:
      cpu:
      memory:

  hostmetrics/disk:
    collection_interval: 1m
    scrapers:
      disk:
      filesystem:

  opencensus:
    endpoint: 0.0.0.0:55678

  # Collect own metrics
  prometheus:
    config:
      scrape_configs:
      - job_name: 'otel-collector'
        scrape_interval: 10s
        static_configs:
        - targets: ['0.0.0.0:8888']

processors:
  batch:

exporters:
  otlphttp:
    endpoint: https://otel.pilet.cloud

service:

  pipelines:

    traces:
      receivers: [otlp, opencensus]
      processors: [batch]
      exporters: [otlphttp]

    metrics:
      receivers: [otlp, opencensus, prometheus, hostmetrics, hostmetrics/disk]
      processors: [batch]
      exporters: [otlphttp]

    logs:
      receivers: [otlp]
      processors: [batch]
      exporters: [otlphttp]

  extensions: [health_check, pprof, zpages]
EOF

service otelcol-contrib restart
