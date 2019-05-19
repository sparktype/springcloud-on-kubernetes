#!/usr/bin/env bash
docker build -t cloudcamp/openjdk11 .
docker tag cloudcamp/openjdk11 cloudcamp/openjdk11:latest
docker push cloudcamp/openjdk11:latest
