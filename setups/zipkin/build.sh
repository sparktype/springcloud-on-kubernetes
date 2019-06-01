#!/usr/bin/env bash
docker build -t cloudcamp/zipkin:2.14.0 . --build-arg .mem_profile
docker push cloudcamp/zipkin:2.14.0
