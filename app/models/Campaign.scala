package models

import play.api.libs.json.Json


/**
 * Campaign data. Represents some static info about campaign.
 */
//TODO: remove stupid options
case class Campaign(id: Option[Long] = None, name: String, link: String, image: String) {
}

object Campaign {
  implicit val todoFmt = Json.format[Campaign]
}