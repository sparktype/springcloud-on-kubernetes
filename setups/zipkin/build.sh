#!/usr/bin/env bash
export ZIPKIN_VERSION=2.18.3
docker build -t dockercamp/zipkin:$ZIPKIN_VERSION . --build-arg .mem_profile
docker push dockercamp/zipkin:$ZIPKIN_VERSION
docker tag dockercamp/zipkin:$ZIPKIN_VERSION dockercamp/zipkin:latest
docker push dockercamp/zipkin:latest

