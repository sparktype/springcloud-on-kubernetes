#!/usr/bin/env bash
minikube start --memory 4096 --vm-driver "hyperkit" --disk-size "10g" --dns-domain=k8s.coolcode.dev
