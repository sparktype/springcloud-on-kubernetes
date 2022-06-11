resource "kubernetes_cluster_role" "cr" {
  metadata {
    name   = "cluster-developer"
    labels = {
      role = "developer"
    }
  }
  rule {
    api_groups = [""]
    resources  = ["*"]
    verbs      = ["get", "list", "watch"]
  }

  rule {
    api_groups = ["*"]
    resources  = ["*"]
    verbs      = ["get", "list", "watch"]
  }
}

resource "kubernetes_cluster_role_binding" "crb" {
  metadata {
    name = "developer-view-cluster-role-binding"
  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-developer"
  }
  subject {
    kind = "ServiceAccount"
    name = "default"
  }
}