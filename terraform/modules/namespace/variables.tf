variable "name" {
  type = string
}
variable "resource_param" {
  type = map(string)
}

variable "create_network_policy" {
  type    = bool
  default = false
}