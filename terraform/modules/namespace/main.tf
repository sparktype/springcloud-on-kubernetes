resource "kubernetes_namespace" "ns" {
  metadata {
    name = var.name

    labels = {
      name    = var.name
      country = var.params["country"]
      profile = var.params["profile"]
    }
  }
}

resource "kubernetes_resource_quota" "quota" {
  metadata {
    name = kubernetes_namespace.ns.metadata[0].labels.name
  }
  spec {
    hard = {
      "pods"          = 10
      "limits.cpu"    = "2"
      "limits.memory" = "8Gi"
    }
  }
}

resource "kubernetes_config_map" "cm" {
  metadata {
    name      = "namespace-config"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  data = {
    "profile" = var.params["profile"]
    "configs" = var.params["configs"]
    "secrets" = var.params["secrets"]
  }
}
