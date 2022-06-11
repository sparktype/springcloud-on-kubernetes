resource "kubernetes_namespace" "ns" {
  metadata {
    name = var.name

    annotations = {
      "field.cattle.io/projectId" = var.resource_param["rancher_annotation"]
    }

    labels = {
      "field.cattle.io/projectId"        = var.resource_param["rancher_projectId"]
      country                            = var.resource_param["country"]
      region                             = var.resource_param["region"]
      zone                               = var.resource_param["availableZone"]
      environment                        = var.resource_param["environment"]
      name                               = var.name
      "goldilocks.fairwinds.com/enabled" = true
    }
  }
}

resource "kubernetes_resource_quota" "quota" {
  metadata {
    name = kubernetes_namespace.ns.metadata[0].labels.name
  }
  spec {
    hard = {
      "pods"          = 100
      "limits.cpu"    = "20"
      "limits.memory" = "100Gi"
    }
  }
}

resource "kubernetes_config_map" "cm" {
  metadata {
    name      = "environment"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  data = {
    "environment"  = var.resource_param["environment"]
    "configserver" = var.resource_param["config_server_url"]
  }
}

resource "kubernetes_network_policy" "np" {
  count = var.create_network_policy ? 1 : 0

  metadata {
    name      = kubernetes_namespace.ns.metadata[0].labels.name
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  spec {
    pod_selector {
      match_labels = {}
    }

    ingress {
      ports {
        protocol = "TCP"
        port     = "8080"
      }

      from {
        ip_block {
          cidr = "10.0.0.0/8"
        }
      }
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