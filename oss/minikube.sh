minikube start --cpus=4 --memory=16g --disk-size=8192

minikube addons enable metallb
minikube addons enable dashboard
minikube addons enable metrics-server

minikube addons configure metallb