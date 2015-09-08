package controllers

import play.api.mvc._
import services.{RedisService, CampaignService}

object Application extends Controller {

  def index = Action { implicit request =>
    val campaigns = CampaignService.get()
    Ok(views.html.index(campaigns))
  }

  def create = Action {
    Ok(views.html.create(CampaignController.campaignForm))
  }

  def campaign(id: Long) = Action {
    val campaign = CampaignService.getById(id)
    val statistics = RedisService.get(id.toString)
    Ok(views.html.campaign(campaign, statistics))
  }

}