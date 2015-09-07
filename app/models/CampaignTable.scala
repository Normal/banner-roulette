package models

import slick.driver.H2Driver.api._

class CampaignTable(tag: Tag) extends Table[Campaign](tag, "CAMPAIGNS") {

  // This is the primary key column:
  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def link: Rep[String] = column[String]("LINK")

  def image: Rep[String] = column[String]("IMAGE")

  def * = (id.?, name, link, image) <>(Campaign.tupled, Campaign.unapply)
}
