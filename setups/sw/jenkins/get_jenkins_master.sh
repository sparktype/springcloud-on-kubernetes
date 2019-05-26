#!/bin/sh
JENKINS_MASTER=`kubectl get pod -n devops -o wide|awk -F " " '{print $6}' | sed -n 2p`
echo "http://$JENKINS_MASTER:8080"
