output "namespace" {
  value = kubernetes_namespace.ns.metadata[0].name
}

output "configmap" {
  value = kubernetes_config_map.cm.metadata[0].name
}
