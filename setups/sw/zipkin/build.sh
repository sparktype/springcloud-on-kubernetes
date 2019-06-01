#!/usr/bin/env bash
docker build -t cloudcamp/zipkin:2.14.0 .
docker push cloudcamp/zipkin:2.14.0
