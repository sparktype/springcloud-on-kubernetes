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
  config_context = "minikube"
  config_path    = "~/.kube/config"
}


module devops {
  source = "./modules/namespace"
  name   = "devops"
  params = var.params
}

module kafka {
  source = "./modules/namespace"
  name   = "kafka"
  params = var.params
}

module data {
  source = "./modules/namespace"
  name   = "data"
  params = var.params
}

module argocd {
  source = "./modules/namespace"
  name   = "argocd"
  params = var.params
}

module argo-rollouts {
  source = "./modules/namespace"
  name   = "argo-rollouts"
  params = var.params
}
