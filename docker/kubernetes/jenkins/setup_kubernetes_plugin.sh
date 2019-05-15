#!/bin/sh
openssl pkcs12 -export -out minikube.pfx -inkey ~/.minikube/apiserver.key -in ~/.minikube/apiserver.crt -certfile ~/.minikube/ca.crt -passout pass:secret
cat ~/.minikube/ca.crt
MASTER_IP=`minikube ip`
echo "Jenkins Master : https://$MASTER_IP:8443"
