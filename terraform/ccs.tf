terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "2.8.0"
    }
  }

  backend "s3" {
    bucket = "terrafrom-state-20200929"
    key = "azure-devops/terraform.tfstate"
    region = "us-east-2"
    dynamodb_table = "terraformlocks-mohit-202009292250"
    encrypt = true
  }
}

provider "kubernetes" {
  config_path    = var.config
  config_context = var.context
}

module global {
  source = "./modules/global"
}

module ccs {
  count                 = var.resource_param["create_ccs"] ? 1 : 0
  source                = "./modules/namespace"
  name                  = "ccs"
  create_network_policy = false
  resource_param        = var.resource_param
}

module kafka {
  source                = "./modules/namespace"
  name                  = "kafka"
  create_network_policy = false
  resource_param        = var.resource_param
}

module metric {
  source                = "./modules/namespace"
  name                  = "metric"
  create_network_policy = false
  resource_param        = var.resource_param
}

module logging {
  source                = "./modules/namespace"
  name                  = "logging"
  create_network_policy = false
  resource_param        = var.resource_param
}

module observability {
  source                = "./modules/namespace"
  name                  = "observability"
  create_network_policy = false
  resource_param        = var.resource_param
}


module gw {
  source                = "./modules/namespace"
  name                  = "gw"
  create_network_policy = false
  resource_param        = var.resource_param
}

#module gw_quota {
#  source     = "./modules/quota"
#  name       = "gw"
#  namespace  = "gw"
#  limits_cpu = "50"
#  limits_mem = "50Gi"
#}


module batch {
  source         = "./modules/namespace"
  name           = "batch"
  resource_param = var.resource_param
}

module policy {
  source         = "./modules/namespace"
  name           = "policy"
  resource_param = var.resource_param
}
#module "policy-rbac" {
#  source = "./modules/rbac"
#  name   = module.policy.namespace
#}


module engine {
  source         = "./modules/namespace"
  name           = "engine"
  resource_param = var.resource_param
}
#module "engine-rbac" {
#  source = "./modules/rbac"
#  name   = module.engine.namespace
#}

module body {
  source         = "./modules/namespace"
  name           = "body"
  resource_param = var.resource_param
}
#module "body-rbac" {
#  source = "./modules/rbac"
#  name   = module.body.namespace
#}

module safety {
  source         = "./modules/namespace"
  name           = "safety"
  resource_param = var.resource_param
}
#module "safety-rbac" {
#  source = "./modules/rbac"
#  name   = module.safety.namespace
#}

module audit {
  source         = "./modules/namespace"
  name           = "audit"
  resource_param = var.resource_param
}
#module "audit-rbac" {
#  source = "./modules/rbac"
#  name   = module.audit.namespace
#}

module metering {
  source         = "./modules/namespace"
  name           = "metering"
  resource_param = var.resource_param
}
#module "metering-rbac" {
#  source = "./modules/rbac"
#  name   = module.metering.namespace
#}

module auth {
  source         = "./modules/namespace"
  name           = "auth"
  resource_param = var.resource_param
}
#module "auth-rbac" {
#  source = "./modules/rbac"
#  name   = module.auth.namespace
#}

module user {
  source         = "./modules/namespace"
  name           = "user"
  resource_param = var.resource_param
}
#module "user-rbac" {
#  source = "./modules/rbac"
#  name   = module.user.namespace
#}

module standard {
  source         = "./modules/namespace"
  name           = "standard"
  resource_param = var.resource_param
}
#module "standard-rbac" {
#  source = "./modules/rbac"
#  name   = module.standard.namespace
#}

module personal {
  source         = "./modules/namespace"
  name           = "personal"
  resource_param = var.resource_param
}
#module "personal-rbac" {
#  source = "./modules/rbac"
#  name   = module.personal.namespace
#}

module vehicle {
  source         = "./modules/namespace"
  name           = "vehicle"
  resource_param = var.resource_param
}
#module "vehicle-rbac" {
#  source = "./modules/rbac"
#  name   = module.vehicle.namespace
#}

module provision {
  source         = "./modules/namespace"
  name           = "provision"
  resource_param = var.resource_param
}
#module "provision-rbac" {
#  source = "./modules/rbac"
#  name   = module.provision.namespace
#}

module notify {
  source         = "./modules/namespace"
  name           = "notify"
  resource_param = var.resource_param
}
#module "notify-rbac" {
#  source = "./modules/rbac"
#  name   = module.notify.namespace
#}

module statistic {
  source         = "./modules/namespace"
  name           = "statistic"
  resource_param = var.resource_param
}
#module "statistic-rbac" {
#  source = "./modules/rbac"
#  name   = module.statistic.namespace
#}

module device {
  source         = "./modules/namespace"
  name           = "device"
  resource_param = var.resource_param
}
#module "device-rbac" {
#  source = "./modules/rbac"
#  name   = module.device.namespace
#}

module consumable {
  source         = "./modules/namespace"
  name           = "consumable"
  resource_param = var.resource_param
}
#module "consumable-rbac" {
#  source = "./modules/rbac"
#  name   = module.consumable.namespace
#}

module behavior {
  source         = "./modules/namespace"
  name           = "behavior"
  resource_param = var.resource_param
}
#module "behavior-rbac" {
#  source = "./modules/rbac"
#  name   = module.behavior.namespace
#}

module vehiclemode {
  source         = "./modules/namespace"
  name           = "vehiclemode"
  resource_param = var.resource_param
}
#module "vehiclemode-rbac" {
#  source = "./modules/rbac"
#  name   = module.vehiclemode.namespace
#}

module drive {
  source         = "./modules/namespace"
  name           = "drive"
  resource_param = var.resource_param
}
#module "drive-rbac" {
#  source = "./modules/rbac"
#  name   = module.drive.namespace
#}

module location {
  source         = "./modules/namespace"
  name           = "location"
  resource_param = var.resource_param
}
#module "location-rbac" {
#  source = "./modules/rbac"
#  name   = module.location.namespace
#}

module inspection {
  source         = "./modules/namespace"
  name           = "inspection"
  resource_param = var.resource_param
}
#module "inspection-rbac" {
#  source = "./modules/rbac"
#  name   = module.inspection.namespace
#}

module diagnostic {
  source         = "./modules/namespace"
  name           = "diagnostic"
  resource_param = var.resource_param
}
#module "diagnostic-rbac" {
#  source = "./modules/rbac"
#  name   = module.diagnostic.namespace
#}

module multimedia {
  source         = "./modules/namespace"
  name           = "multimedia"
  resource_param = var.resource_param
}
#module "multimedia-rbac" {
#  source = "./modules/rbac"
#  name   = module.multimedia.namespace
#}

module cfs {
  source         = "./modules/namespace"
  name           = "cfs"
  resource_param = var.resource_param
}

module "service" {
  source   = "./modules/service"
  sbs_host = var.sbs_host
  sbs_port = var.sbs_port
}

module vas {
  source         = "./modules/namespace"
  name           = "vas"
  resource_param = var.resource_param
}