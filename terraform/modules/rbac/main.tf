resource "kubernetes_role" "r" {
  metadata {
    name      = var.name
    namespace = var.name
  }

  rule {
    api_groups = [
      "", "apps", "configmaps", "pods", "services", "endpoints", "autoscaling", "extensions", "namespaces",
      "batch", "policy", "networking.k8s.io", "argoproj.io"
    ]
    resources = ["*"]
    verbs     = ["*"]
  }
}

resource "kubernetes_role_binding" "rb" {
  metadata {
    name      = format("developer-%s-role-binding", var.name)
    namespace = var.name

  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "Role"
    name      = kubernetes_role.r.metadata[0].name
  }
  subject {
    kind      = "ServiceAccount"
    name      = "default"
    namespace = var.name
  }
}


resource "kubernetes_cluster_role_binding" "crb" {
  metadata {
    name = format("developer-%s-cluster-role-binding", var.name)
  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-developer"
  }
  subject {
    kind      = "ServiceAccount"
    name      = "default"
    namespace = var.name
  }
}