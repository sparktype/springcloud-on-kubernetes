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

module users {
  source = "./modules/namespace"
  name   = "users"
  params = var.params
}

module stock {
  source = "./modules/namespace"
  name   = "stock"
  params = var.params
}

module ship {
  source = "./modules/namespace"
  name   = "ship"
  params = var.params
}

module sale {
  source = "./modules/namespace"
  name   = "sale"
  params = var.params
}

module product {
  source = "./modules/namespace"
  name   = "product"
  params = var.params
}

module oauth {
  source = "./modules/namespace"
  name   = "oauth"
  params = var.params
}

module gateway {
  source = "./modules/namespace"
  name   = "gateway"
  params = var.params
}