#!/usr/bin/env bash

keytool -genkeypair -alias spring -keyalg RSA \
       -dname "CN=Auth,OU=,O=springcloud,S=Seoul,C=KR" \
       -keypass spring -storepass spring \
       -keystore spring.jks

keytool -export -keystore spring.jks -alias spring -storepass spring -file spring.cer

openssl x509 -inform der -in ./spring.cer -pubkey -noout > spring.pub
