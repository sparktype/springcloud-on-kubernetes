resource "kubernetes_service" "kafka1" {
  metadata {
    name      = "ccs-kafka-0.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "kafka"
    }
  }
  spec {
    cluster_ip = "None"
    port {
      port        = var.kafka_port1
      protocol    = "TCP"
      target_port = var.kafka_port1
    }
  }
}

resource "kubernetes_endpoints" "kafka1" {
  metadata {
    name      = "ccs-kafka-0.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "sbs"
    }
  }
  subset {
    address {
      ip = var.kafka_host1
    }
    port {
      port = var.kafka_port1
    }
  }
}

resource "kubernetes_service" "kafka2" {
  metadata {
    name      = "ccs-kafka-1.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "kafka"
    }
  }
  spec {
    cluster_ip = "None"
    port {
      port        = var.kafka_port2
      protocol    = "TCP"
      target_port = var.kafka_port2
    }
  }
}

resource "kubernetes_endpoints" "kafka2" {
  metadata {
    name      = "ccs-kafka-1.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "kafka"
    }
  }
  subset {
    address {
      ip = var.kafka_host2
    }
    port {
      port = var.kafka_port2
    }
  }
}

resource "kubernetes_service" "kafka3" {
  metadata {
    name      = "ccs-kafka-2.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "kafka"
    }
  }
  spec {
    cluster_ip = "None"
    port {
      port        = var.kafka_port3
      protocol    = "TCP"
      target_port = var.kafka_port3
    }
  }
}

resource "kubernetes_endpoints" "kafka3" {
  metadata {
    name      = "ccs-kafka-2.ccs-kafka-brokers"
    namespace = "kafka"
    labels    = {
      "app" = "kafka"
    }
  }
  subset {
    address {
      ip = var.kafka_host3
    }
    port {
      port = var.kafka_port3
    }
  }
}