resource "kubernetes_service" "sbs" {
  metadata {
    name      = "sbs"
    namespace = "gw"
    labels    = {
      "app" = "sbs"
    }
  }
  spec {
    cluster_ip = "None"
    port {
      port        = var.sbs_port
      protocol    = "TCP"
      target_port = var.sbs_port
    }
  }
}

resource "kubernetes_endpoints" "sbs" {
  metadata {
    name      = "sbs"
    namespace = "gw"
    labels    = {
      "app" = "sbs"
    }
  }
  subset {
    address {
      ip = var.sbs_host
    }
    port {
      port = var.sbs_port
    }
  }
}