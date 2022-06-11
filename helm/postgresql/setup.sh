helm repo add bitnami https://charts.bitnami.com/bitnami
helm install postgresql bitnami/postgresql -f values.yaml -n dbms