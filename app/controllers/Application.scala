package controllers

import models.Campaign
import play.api.mvc._

object Application extends Controller {

  def index = Action { implicit request =>
    val campaign = Campaign(Some(1), "name1", "some link", null)
    Ok(views.html.index(campaign))
  }

  def create = Action {
    Ok(views.html.create(CampaignController.campaignForm))
  }

}