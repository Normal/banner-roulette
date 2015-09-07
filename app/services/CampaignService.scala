package services

import models.{Campaign, CampaignTable}
import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object CampaignService {

  val db = Database.forConfig("db.default")

  def get(): Seq[Campaign] = {
    try {
      val campaigns = TableQuery[CampaignTable]
      Await.result(db.run(campaigns.result), Duration.Inf)
    } finally db.close()
  }

//  def save(campaign: Campaign) {
//    db.run()
//  }
}
