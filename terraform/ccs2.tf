terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
    }

    aws = {
      source  = "hashicorp/aws"
    }
  }

  backend "s3" {
    bucket = "ccs-terraforming-state"
    workspace_key_prefix = "ccs"
    key = "terraform.tfstate"
    region = "ap-northeast-2"
    dynamodb_table = "ccs-terraforming-locks"
    encrypt = true
  }
}

provider "kubernetes" {
  config_path    = var.config
  config_context = var.context
}

module ccs {
  count                 = var.create_ccs ? 1 : 0
  source                = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name                  = "ccs"
  
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module kafka {
  source                = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name                  = "kafka"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module metric {
  source                = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name                  = "metric"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module gw {
  source                = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name                  = "gw"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module batch {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "batch"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module argocd {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "argocd"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module redis {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "redis"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module safety {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "safety"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module remote {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "remote"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module body {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "body"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module engine {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "engine"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module device {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "device"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module diagnotics {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "diagnotics"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module location {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "location"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module multimedia {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "multimedia"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module notification {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "notification"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module drive {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "drive"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module maintenance {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "maintenance"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module ivr {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "ivr"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

module vehiclemode {
  source         = "git::https://code.hcloud.hmc.co.kr/h2/terraform/namespace.git"
  name           = "vehiclemode"
  country            = var.country
  region             = var.region
  zone               = var.zone
  rancher_projectId  = var.rancher_projectId
  rancher_annotation = var.rancher_annotation
  environment        = var.environment
  config_server_url  = var.config_server_url
}

#module "service" {
#  source   = "./modules/service"
#  sbs_host = var.sbs_host
#  sbs_port = var.sbs_port
#}

