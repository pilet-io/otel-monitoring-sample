#!/bin/bash

export JAVA_TOOL_OPTIONS='-javaagent:./lib/opentelemetry-javaagent.jar'
export OTEL_EXPORTER_OTLP_ENDPOINT='https://otel.pilet.cloud'
export OTEL_EXPORTER_OTLP_PROTOCOL='http/protobuf'
export OTEL_JAVAAGENT_DEBUG=false
export OTEL_LOG_EXPORTER=otlp
export OTEL_METRIC_EXPORTER=otlp
export OTEL_RESOURCE_ATTRIBUTES='service.version=1.2,deployment.environment=qa'
export OTEL_SERVICE_NAME=otel-monitoring-sample

export SPRING_PROFILES_ACTIVE=embedded

java -jar app.jar