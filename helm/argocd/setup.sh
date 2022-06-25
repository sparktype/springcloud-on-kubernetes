kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d && echo

argocd login 10.112.211.11:80 --plaintext --username admin

argocd login --insecure 10.11.88.65:80


argocd cluster add ap-northeast-2-dev-admin --name ap-northeast-2-dev --upsert
argocd cluster add ap-northeast-2-stg-admin --name ap-northeast-2-stg --upsert


IVkB8FRfo4DdaanHFMkpkqGGBbPSUAWt