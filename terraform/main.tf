terraform {
  cloud {
    organization = "sparktype"

    workspaces {
      name = "springcloud-on-kubernetes"
    }
  }
}

