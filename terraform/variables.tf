variable "context" {
  type    = string
  default = ""
}

variable "config" {
  type    = string
  default = "~/.kube/config"
}

variable "resource_param" {
  type = map(string)
}

variable "sbs_host" {
  type = string
}

variable "sbs_port" {
  type = string
}

# ap-northeast-arc : 원자로 (성남)
# ap-northeast-1 : 1센터 (의왕)
# ap-northeast-2 : 2센터 (상암)
# ap-southeast-1 : 1센터 (싱가폴)
# ap-southeast-2 : 2센터 (싱가폴)
# us-west-1 : 1센터 (LA)
# us-west-2 : 2센터 (Sanfrancisco)
# eu-west-1 : 1센터 
# eu-west-2 : 2센터 
