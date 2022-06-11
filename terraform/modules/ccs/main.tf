resource "kubernetes_namespace" "ns" {
  metadata {
    name = "ccs"
    
    annotations = {
      "field.cattle.io/projectId" = var.resource_param["rancher_annotation"]
    }

    labels = {
      "field.cattle.io/projectId": var.resource_param["rancher_projectId"]
      country = var.resource_param["country"]
      region = var.resource_param["region"]
      zone = var.resource_param["availableZone"]
      environment = var.resource_param["environment"]
      name = "ccs"
    }
  }
}

resource "kubernetes_config_map" "cm" {
  metadata {
    name = "environment"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  data = {
    "environment" = var.resource_param["environment"]
    "configserver" = var.resource_param["config_server_url"]
  }
}

resource "kubernetes_network_policy" "np" {
  metadata {
    name      = kubernetes_namespace.ns.metadata[0].labels.name
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  spec {
    pod_selector {
      match_labels = {}
    }

    ingress {
      from {
        namespace_selector {
          match_labels = {
            name = "ccs"
          }
        }
      }
    }
    egress {}
    policy_types = ["Ingress", "Egress"]
  }

}


