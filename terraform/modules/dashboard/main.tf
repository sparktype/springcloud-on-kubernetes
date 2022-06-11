resource "kubernetes_namespace" "ns" {
  metadata {
    name = "kubernetes-dashboard"

    annotations = {
      "field.cattle.io/projectId" = var.resource_param["rancher_annotation"]
    }

    labels = {
      "field.cattle.io/projectId": var.resource_param["rancher_projectId"]
      country = var.resource_param["country"]
      region = var.resource_param["region"]
      zone = var.resource_param["availableZone"]
      environment = var.resource_param["environment"]
      name = "kubernetes-dashboard"
    }
  }
}

resource "kubernetes_service_account" "account" {
  metadata {
    name = "kubernetes-dashboard"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }
}

resource "kubernetes_service" "service" {
  metadata {
    name = "kubernetes-dashboard"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  spec {
    selector  = {
        "k8s-app": "kubernetes-dashboard"
    }

    port {
      port = 9090
      target_port = 9090
    }
  }
}

resource "kubernetes_secret" "secret_certs" {
  metadata {
    name = "kubernetes-dashboard-certs"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  type = "Opaque"
}

resource "kubernetes_secret" "secret_csrf" {
  metadata {
    name = "kubernetes-dashboard-csrf"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  type = "Opaque"
  data = {
    csrf = ""
  }
}

resource "kubernetes_secret" "secret_key" {
  metadata {
    name = "kubernetes-dashboard-key-holder"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  type = "Opaque"
}


resource "kubernetes_config_map" "cm" {
  metadata {
    name = "kubernetes-dashboard-settings"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
}

resource "kubernetes_role" "role" {
  metadata {
    name = "kubernetes-dashboard"
    namespace = kubernetes_namespace.ns.metadata[0].name
  }

  rule {
    api_groups = [""]
    resources  = ["secrets"]
    resource_names = ["kubernetes-dashboard-certs", "kubernetes-dashboard-csrf", "kubernetes-dashboard-key-holder"]
    verbs      = ["get", "update", "delete"]
  }

  rule {
    api_groups = [""]
    resources  = ["configmaps"]
    resource_names = ["kubernetes-dashboard-settings"]
    verbs      = ["get", "update"]
  }

  rule {
    api_groups = [""]
    resources  = ["services"]
    resource_names = ["heapster", "dashboard-metrics-scraper"]
    verbs      = ["proxy"]
  }

  rule {
    api_groups = [""]
    resources  = ["services/proxy"]
    resource_names = ["heapster",  "http:heapster:", "https:heapster:", "dashboard-metrics-scraper", "http:dashboard-metrics-scraper"]
    verbs      = ["get"]
  }

}

resource "kubernetes_cluster_role" "cluster_role" {
  metadata {
    name = "kubernetes-dashboard"
  }

  rule {
    api_groups = ["metrics.k8s.io"]
    resources  = ["pods", "nodes"]
    verbs = ["get", "list", "watch"]
  }
}

resource "kubernetes_role_binding" "role_binding" {
  metadata {
    name = "kubernetes-dashboard"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "Role"
    name      = "kubernetes-dashboard"
  }
  subject {
    kind = "ServiceAccount"
    name = "kubernetes-dashboard"
    namespace = "kubernetes-dashboard"
  }
}

resource "kubernetes_cluster_role_binding" "cluster_role_binding" {
  metadata {
    name = "kubernetes-dashboard"
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "kubernetes-dashboard"
  }
  subject {
    kind = "ServiceAccount"
    name = "kubernetes-dashboard"
    namespace = "kubernetes-dashboard"
  }
}

resource "kubernetes_deployment" "deploy" {
  metadata {
    name = "kubernetes-dashboard"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "kubernetes-dashboard"
    }
  }
  spec {
    replicas = 1
    revision_history_limit = 10
    selector {
      match_labels = {
        "k8s-app": "kubernetes-dashboard"
      }
    }
    template {
      metadata {
        labels = {
          "k8s-app": "kubernetes-dashboard"
        }
      }
      spec {
        container {
          name = "kubernetes-dashboard"
          image = "kubernetesui/dashboard:v2.4.0"
          image_pull_policy = "Always"

          port {
            container_port = 9090
            protocol = "TCP"
          }

          args = [
            "--namespace=kubernetes-dashboard",
            "--insecure-bind-address=0.0.0.0",
            "--insecure-port=9090",
            "--enable-insecure-login"
          ]

          volume_mount {
            mount_path = "/certs"
            name       = "kubernetes-dashboard-certs"
          }

          volume_mount {
            mount_path = "/tmp"
            name       = "tmp-volume"
          }

          liveness_probe {
            http_get {
              path = "/"
              port = 9090
            }
            initial_delay_seconds = 30
            timeout_seconds = 30
          }

          security_context {
            allow_privilege_escalation = false
            read_only_root_filesystem = true
            run_as_user = "1001"
            run_as_group = "2001"
          }
        }
        volume {
          name = "kubernetes-dashboard-certs"
          secret {
            secret_name = "kubernetes-dashboard-certs"
          }
        }
        volume {
          name = "tmp-volume"
          empty_dir {}
        }
        service_account_name = "kubernetes-dashboard"
        node_selector = {
          "beta.kubernetes.io/os": "linux"
        }
        toleration {
          key = "node-role.kubernetes.io/master"
          effect = "NoSchedule"
        }
      }
    }
  }
}

resource "kubernetes_service" "service_metrics" {
  metadata {
    name = "dashboard-metrics-scraper"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "dashboard-metrics-scraper"
    }
  }
  spec {
    port {
      port = 8000
      target_port = 8000
    }
    selector = {
      "k8s-app": "dashboard-metrics-scraper"
    }
  }
}

resource "kubernetes_deployment" "deploy_metrics" {
  metadata {
    name = "dashboard-metrics-scraper"
    namespace = kubernetes_namespace.ns.metadata[0].name
    labels = {
      "k8s-app": "dashboard-metrics-scraper"
    }
  }
  spec {
    replicas = "1"
    revision_history_limit = "10"
    selector {
      match_labels = {
        "k8s-app": "dashboard-metrics-scraper"
      }
    }
    template {
      metadata {
        labels = {
          "k8s-app": "dashboard-metrics-scraper"
        }
      }
      spec {
#        security_context {
#          seccomp_profile {
#            type: "RuntimeDefault"
#          }
#        }

        container {
          name = "dashboard-metrics-scraper"
          image = "kubernetesui/metrics-scraper:v1.0.7"
          port {
            container_port = 8000
            protocol = "TCP"
          }

          liveness_probe {
            http_get {
              scheme = "HTTP"
              path = "/"
              port = 8000
            }
            initial_delay_seconds = 30
            timeout_seconds = 30
          }

          volume_mount {
            mount_path = "/tmp"
            name       = "tmp-volume"
          }

          security_context {
            allow_privilege_escalation = false
            read_only_root_filesystem = true
            run_as_user = "1001"
            run_as_group = "2001"
          }
        }

        service_account_name = "kubernetes-dashboard"
        node_selector = {
          "beta.kubernetes.io/os": "linux"
        }
        toleration {
          key = "node-role.kubernetes.io/master"
          effect = "NoSchedule"
        }
        volume {
          name = "tmp-volume"
          empty_dir {}
        }
      }
    }
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


