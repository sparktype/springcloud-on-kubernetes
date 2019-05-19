#!/bin/sh
cp ~/.minikube/ca.crt .
cp ~/.minikube/client.crt .
cp ~/.minikube/client.key .
docker build . -t hubtea/kubectl
docker push hubtea/kubectl