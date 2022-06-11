context = "ap-northeast-1-stg"

resource_param = {
  country            = "kr"
  region             = "ap-northeast"
  availableZone      = "ap-northeast-1"
  environment        = "stg-1"
  rancher_projectId  = "p-vfcks"
  rancher_annotation = "c-tgzxf:p-vfcks"
  config_server_url  = "optional:configserver:http://config.ccs.svc.cluster.local:8888"
  create_ccs         = false
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