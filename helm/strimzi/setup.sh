helm install strimzi strimzi/strimzi-kafka-operator -n kafka

kubectl apply -f kafka.yaml -n kafka