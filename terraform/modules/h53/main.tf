resource "aws_route53_zone" "hosted_zone" {
  name = "ccs.h53"
}

resource "aws_route53_record" "ccs" {
  zone_id = aws_route53_zone.hosted_zone.zone_id
  name    = "${var.host}.${aws_route53_zone.hosted_zone.name}"
  type    = "A"
  ttl     = "60"
  records = var.records
}
