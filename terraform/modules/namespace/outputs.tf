 output "namespace" {
   value = kubernetes_namespace.ns.metadata[0].name
 }

 output "configmap" {
   value = kubernetes_config_map.cm.metadata[0].name
 }

 output "network-policy" {
   value = var.create_network_policy ? kubernetes_network_policy.np[0].metadata[0].name: ""
 }