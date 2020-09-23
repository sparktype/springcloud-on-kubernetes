#!/usr/bin/env bash

kubectl apply -f springcloud-eureka/deployment.yaml
kubectl apply -f springcloud-hystrix/deployment.yaml
kubectl apply -f springcloud-zuul/deployment.yaml
kubectl apply -f springcloud-security/deployment.yaml
kubectl apply -f api-user/deployment.yaml
kubectl apply -f api-good/deployment.yaml
kubectl apply -f api-sale/deployment.yaml
kubectl apply -f api-ship/deployment.yaml