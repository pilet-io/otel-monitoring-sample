#!/bin/bash

sudo apt-get update
sudo apt-get -y install wget systemctl
wget https://github.com/open-telemetry/opentelemetry-collector-releases/releases/download/v0.105.0/otelcol-contrib_0.105.0_linux_386.deb
sudo dpkg -i otelcol-contrib_0.105.0_linux_386.deb
