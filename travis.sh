#!/usr/bin/env bash

if [ -z "$1" ]
  then
    echo "Example : travis.sh docker-user docker-pass"
    exit
fi

travis encrypt DOCKER_USER=$1 --add
travis encrypt DOCKER_PASS=$2 --add
