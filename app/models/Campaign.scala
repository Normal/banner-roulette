package models

import play.api.libs.json.Json
import slick.driver.H2Driver.api._


/**
 * Campaign data. Represents some static info about campaign. Stored in h2.
 */
case class Campaign(id: Option[Long] = None, name: String, link: String, image: String) {
}

/**
 * Common data for all Campaign objects.
 */
object Campaign {
  //init json serialization for Campaign
  implicit val todoFmt = Json.format[Campaign]
}

/**
 * ORM mapping on db Table.
 */
class CampaignTable(tag: Tag) extends Table[Campaign](tag, "CAMPAIGNS") {

  // This is the primary key column:
  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def link: Rep[String] = column[String]("LINK")

  def image: Rep[String] = column[String]("IMAGE")

  def * = (id.?, name, link, image) <>((Campaign.apply _).tupled, Campaign.unapply)
}