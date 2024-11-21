#!/bin/bash


export OTEL_SERVICE_NAME="otel"
export OTEL_EXPORTER_OTLP_TRACES_ENDPOINT="http://127.0.0.1:4318/v1/traces"
export OTEL_EXPORTER_OTLP_PROTOCOL="http/protobuf"

opentelemetry-instrument --traces_exporter console,otlp uvicorn app:app &