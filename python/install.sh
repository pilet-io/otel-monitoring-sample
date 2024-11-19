#!/bin/bash

#>

pip3 install opentelemetry-distro opentelemetry-exporter-otlp opentelemetry-instrumentation-fastapi opentelemetry-instrumentation-flask
opentelemetry-bootstrap -a install