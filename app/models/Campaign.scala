package models


/**
 * Campaign data. Represents some static info about campaign.
 */
case class Campaign(id: Option[Long] = None, name: String, link: String, image: String)