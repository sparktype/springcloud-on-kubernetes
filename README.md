[![Java CI with Gradle](https://github.com/sparktype/springcloud-on-kubernetes/actions/workflows/gradle.yml/badge.svg)](https://github.com/sparktype/springcloud-on-kubernetes/actions/workflows/gradle.yml)
![](https://github.com/clustercamp/springcloud-java/workflows/build/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/clustercamp/springcloud-java/badge.svg?branch=master)](https://coveralls.io/github/clustercamp/springcloud-java?branch=master)

![Logo](https://repository-images.githubusercontent.com/177172824/907da800-f91e-11e9-8a13-415ff32e13cd)

# springcloud-java

Building micro service with spring cloud and Java on kuberntes

## Build

```shell script
$ ./gradlew clean build
```

## Build Image with JIB

```shell script
$ ./gradlew jib 
``` 

## Kubernetes Settings

### Minikube

```shell
$ minikube start

$ minikube addons enable metallb
$ minikube addons enable dashboard
$ minikube addons enable metrics-server
```

### Hashicorp Consul

### Hashicorp Vault
