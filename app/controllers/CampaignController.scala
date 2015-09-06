package controllers

import models.Campaign
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

object CampaignController extends Controller {

  val campaignForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText,
      "link" -> nonEmptyText,
      "image" -> nonEmptyText
    )(Campaign.apply)(Campaign.unapply)
  )

  def createCampaign = Action { implicit request =>
    campaignForm.bindFromRequest.fold(
      hasErrors => {
        Logger.debug("Some form validation error occurs")
        BadRequest(views.html.create(hasErrors))
      },
      campaign => {
        Logger.debug("Campaign object successfully obtains")
        Redirect(routes.Application.index()).flashing("success" -> "Contact saved!")
      }
    )
  }
}
