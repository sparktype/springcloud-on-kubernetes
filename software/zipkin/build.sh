#!/usr/bin/env bash
docker build -t cloudcamp/zipkin .
docker tag cloudcamp/zipkin cloudcamp/zipkin:latest
docker push cloudcamp/zipkin:latest
