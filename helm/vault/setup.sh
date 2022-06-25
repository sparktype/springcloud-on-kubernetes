helm repo add hashicorp https://helm.releases.hashicorp.com
helm install vault hashicorp/vault -f values.yaml -n devops


kubectl exec vault-0 -n devops -- vault operator init -key-shares=1 -key-threshold=1 -format=json > cluster-keys.json

# US
#Unseal Key 1: Kj0Dd9by8xkkBjlDiZEp4FfGe7yRdnWVscFXvx5fX2qt
#Unseal Key 2: FTuzZwFAs41xRJXDYtv9Q5Ttj4/7LM/lEys82z/ux+qE
#Unseal Key 3: VVEU7X5yyhsVO5mrmmB+tn8wVKK7Oh2xAWF8ywcLLATe
#Unseal Key 4: oKMw2B6dRLz+dLitSaQjtqV0bHVFXVURjxbVrZ+Rmu5H
#Unseal Key 5: b2tuaMzF06T6zwbONpWKyGfXgVFEnGx+b/NpctpoqoIa
#
#Initial Root Token: s.X0CJTTkcwXBMeYSBHX3wv8Ke