package controllers


import java.io.{File, FileInputStream}
import javax.imageio.ImageIO

import models.Campaign
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.{Utils, RedisService, CampaignService}

import scala.util.Random

/**
 * Handles special Campaign requests.
 */
object CampaignController extends Controller {

  /**
   * Map HTML form to Campaign model object.
   */
  val campaignForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText,
      "link" -> nonEmptyText
    )
      ((id, name, link) => Campaign(id, name, link))
      ((campaign: Campaign) => Some(campaign.id, campaign.name, campaign.link))
  )

  /**
   * Creates new Campaign.
   */
  def createCampaign = Action(parse.multipartFormData) { implicit request =>
    request.body.file("image").map {
      picture =>
        campaignForm.bindFromRequest.fold(
          hasErrors => {
            Logger.debug("Some form validation error occurs")
            BadRequest(views.html.create(hasErrors))
          },
          campaign => {
            Logger.debug("Campaign object successfully obtains")
            val id: Long = CampaignService.save(campaign)

            val is = new FileInputStream(picture.ref.file)
            val bufferedThumbnail = Utils.resizeImage(is)
            val file: File = new File(s"public/images/icons/$id.jpg")
            file.createNewFile()
            ImageIO.write(bufferedThumbnail, "jpeg", file)
          })
        Redirect(routes.Application.index()).flashing("success" -> "Contact saved!")
    }.getOrElse {
      Redirect(routes.Application.index()).flashing(
        "error" -> "Missing file")
    }
  }

  /**
   * Returns random campaign.
   */
  def random = Action {
    //ugly solution
    //Its preferable to choose random item on DB side
    val campaigns = CampaignService.get()
    val randomIndex = new Random(System.currentTimeMillis()).nextInt(campaigns.length)
    val randomCampaign: Campaign = campaigns(randomIndex)

    //Not sure is it correct, I guess if client requested random campaign - it will viewed anyway
    RedisService.increment(randomCampaign.id.get.toString, "views")

    Ok(Json.toJson(randomCampaign))
      //allows CORS
      .withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")
  }
}