package services

import models.{Campaign, CampaignTable}
import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object CampaignService {

  val db = Database.forConfig("db.default")

  val campaigns = TableQuery[CampaignTable]

  def get(): Seq[Campaign] = {
    Await.result(db.run(campaigns.result), Duration.Inf)
  }

  def getById(id: Long): Campaign = {
    Await.result(db.run(campaigns.filter {
      _.id === id
    }.result), Duration.Inf).head
  }

  def save(campaign: Campaign) {
    db.run(campaigns += campaign)
  }
}
