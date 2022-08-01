resource "kubernetes_namespace" "ns" {
  metadata {
    name = var.name

    labels = {
      name = var.name
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
    name      = "configs"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  data = {
    "profile"     = var.params["profile"]
    "consul_host" = var.params["consul_host"]
    "consul_port" = var.params["consul_port"]
  }
}
