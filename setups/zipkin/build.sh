#!/usr/bin/env bash
docker build -t dockercamp/zipkin:2.15.0 . --build-arg .mem_profile
docker push dockercamp/zipkin:2.15.0
docker tag dockercamp/zipkin:2.15.0 dockercamp/zipkin:latest
docker push dockercamp/zipkin:latest

