helm repo add hashicorp https://helm.releases.hashicorp.com
helm install consul hashicorp/consul -f values.yaml -n devops


kubeclt export