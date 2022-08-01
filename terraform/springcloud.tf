terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "2.11.0"
    }
  }

  cloud {
    organization = "sparktype"

    workspaces {
      name = "springcloud-on-kubernetes"
    }
  }
}

provider "kubernetes" {
  config_context = "docker-desktop"
  config_path    = "~/.kube/config"
}

module kafka {
  source = "./modules/namespace"
  name   = "kafka"
  params = var.params
}

module users {
  source = "./modules/namespace"
  name   = "users"
  params = var.params
}

