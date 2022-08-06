
# Install Component

## Metric Server

```shell
helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/
helm upgrade --install metrics-server metrics-server/metrics-server -f metric-pgsql-values.yaml -n kube-system
```

## Consul

```shell
helm repo add hashicorp https://helm.releases.hashicorp.com
helm install consul hashicorp/consul --create-namespace -n consul -f consul-pgsql-values.yaml
```


## Vault

```shell
helm repo add hashicorp https://helm.releases.hashicorp.com
helm install vault hashicorp/vault --create-namespace -n vault -f vault-pgsql-values.yaml

kubectl exec vault-0 -n devops -- vault operator init -key-shares=1 -key-threshold=1 -format=json > cluster-keys.json
```

### Initialize and unseal Vault 

```shell
kubectl exec vault-0 -n vault -- vault operator init -key-shares=1 -key-threshold=1 -format=json > cluster-keys.json
cat cluster-keys.json | jq -r ".unseal_keys_b64[]"

8XBuKuosYluCDL7N9MDgorf9l+n66UqXDQWMDqOp//Y=
VAULT_UNSEAL_KEY=$(cat cluster-keys.json | jq -r ".unseal_keys_b64[]")
kubectl exec vault-0 -n vault -- vault operator unseal $VAULT_UNSEAL_KEY
kubectl exec vault-1 -n vault -- vault operator unseal $VAULT_UNSEAL_KEY
kubectl exec vault-2 -n vault -- vault operator unseal $VAULT_UNSEAL_KEY
````

```shell
export VAULT_ADDR=http://localhost:30082
vault operator init
hvs.u5iap5Wx11oRpZByaJQAddWA
export VAULT_TOKEN=hvs.u5iap5Wx11oRpZByaJQAddWA
8XBuKuosYluCDL7N9MDgorf9l+n66UqXDQWMDqOp//Y=


❯ vault token create
Key                  Value
---                  -----
token                hvs.mQhKUlKaVUvyrynSEvBwDkCM
token_accessor       qWzulkO9BgBEpFhJjlaMlojF
token_duration       ∞
token_renewable      false
token_policies       ["root"]
identity_policies    []
policies             ["root"]

❯ vault token create -policy=default
Key                  Value
---                  -----
token                hvs.CAESILVFhkFkryy5QRNyrPYOjyIvfOnwJgQxIw7MDgnt49q8Gh4KHGh2cy5rRVhNZjV4VUl1dVhtV3NNUmo5ZXFtSDI
token_accessor       YPI6xn4tkKhvXkUTGIKkBRp5
token_duration       768h
token_renewable      true
token_policies       ["default"]
identity_policies    []
policies             ["default"]

❯ vault token create -policy=springcloud
Key                  Value
---                  -----
token                hvs.CAESIDYDqotw7x7F5DUcyFImeh1SVcQx79ls8wrsT3qZR0geGh4KHGh2cy5tTUs4dU9qeEJ3bWdzT2dITEd5amQ5Vkk
token_accessor       CezXMg9Ii6vRrEfnbdHdaJvS
token_duration       768h
token_renewable      true
token_policies       ["default" "springcloud"]
identity_policies    []
policies             ["default" "springcloud"]
```

https://learn.hashicorp.com/tutorials/vault/kubernetes-minikube-consul

## ArgoCD

```shell
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml


kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d && echo
```

### Argo Rollout

```shell
kubectl create namespace argo-rollouts
kubectl apply -n argo-rollouts -f https://github.com/argoproj/argo-rollouts/releases/download/v1.3.0-rc1/install.yaml

```

### User Management

```shell
argocd login 10.112.211.11:80 --plaintext --username admin
argocd login --insecure 10.11.88.65:80
argocd cluster add ap-northeast-2-dev-admin --name ap-northeast-2-dev --upsert
argocd cluster add ap-northeast-2-stg-admin --name ap-northeast-2-stg --upsert
```

## Strimzi Kafka Operator

```shell
helm repo add strimzi https://strimzi.io/charts/
helm install strimzi strimzi/strimzi-kafka-operator --create-namespace -n kafka
kubectl apply -f kafka.yaml -n kafka
```

## PostgreSQL

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install pgsql bitnami/postgresql --create-namespace -n db -f pgsql-values.yaml
```