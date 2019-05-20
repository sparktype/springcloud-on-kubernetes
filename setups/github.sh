#!/usr/bin/env bash
echo "Please enter your Github username";
read username;

echo "Please enter your Github password";
read password;

echo "Creating secret"
kubectl create secret generic github -n springcloud --from-literal=username=${username} --from-literal=password=${password}
