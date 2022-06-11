output "role" {
  value = kubernetes_role.r.metadata[0].name
}

output "role_binding" {
  value = kubernetes_role_binding.rb.metadata[0].name
}

#output "cluster_role" {
#  value = kubernetes_cluster_role.cr.metadata[0].name
#}
#
#output "cluster_role_binding" {
#  value = kubernetes_cluster_role_binding.crb.metadata[0].name
#}