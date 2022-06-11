context = "ap-northeast-2-dev-admin"

resource_param = {
  country            = "kr"
  region             = "ap-northeast"
  availableZone      = "ap-northeast-2"
  environment        = "dev"
  rancher_projectId  = "c-4rdw2"
  rancher_annotation = "c-4rdw2:p-vdbwb"
  config_server_url  = "optional:configserver:http://config.ccs.svc.cluster.local:8888/"
  create_ccs         = true
  create_redis       = true
}
region   = "ap-northeast"
sbs_host = "10.11.88.45"
sbs_port = "8003"
# ap-northeast-arc : 원자로 (성남)
# ap-northeast-1 : 1센터 (의왕)
# ap-northeast-2 : 2센터 (상암)
# ap-southeast-1 : 1센터 (싱가폴)
# ap-southeast-2 : 2센터 (싱가폴)
# us-west-1 : 1센터 (LA)
# us-west-2 : 2센터 (Sanfrancisco)
# eu-west-1 : 1센터 
# eu-west-2 : 2센터 

