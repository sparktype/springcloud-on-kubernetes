FROM alpine
LABEL name="Leonardo Park"
USER root
WORKDIR /usr/local/bin
ENV HOME=/config 
RUN apk add --no-cache curl ca-certificates
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
RUN chmod +x ./kubectl
RUN mkdir -p /config/.kube && mkdir -p /config/.minikube
ADD config /config/.kube/
ADD ca.crt /config/.minikube/
ADD client.crt /config/.minikube/
ADD client.key /config/.minikube/
RUN mv ./kubectl /usr/local/bin/kubectl