package controllers


import java.awt.Image
import java.awt.image.BufferedImage
import java.io.{ByteArrayOutputStream, FileInputStream}
import javax.imageio.ImageIO

import models.Campaign
import org.apache.commons.codec.binary.Base64
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.{RedisService, CampaignService}

import scala.util.Random

object CampaignController extends Controller {

  val campaignForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText,
      "link" -> nonEmptyText
    )
      ((id, name, link) => Campaign(id, name, link, null))
      ((campaign: Campaign) => Some(campaign.id, campaign.name, campaign.link))
  )

  def createCampaign = Action(parse.multipartFormData) { implicit request =>
    request.body.file("image").map {
      picture =>
        val is = new FileInputStream(picture.ref.file)
        val bytes = resizeImage(is)

        campaignForm.bindFromRequest.fold(
          hasErrors => {
            Logger.debug("Some form validation error occurs")
            BadRequest(views.html.create(hasErrors))
          },
          campaign => {
            Logger.debug("Campaign object successfully obtains")
            CampaignService.save(campaign.copy(image = new String(Base64.encodeBase64(bytes))))
          })
        Redirect(routes.Application.index()).flashing("success" -> "Contact saved!")
    }.getOrElse {
      Redirect(routes.Application.index()).flashing(
        "error" -> "Missing file")
    }
  }

  private def resizeImage(is: FileInputStream): Array[Byte] = {
    val os = new ByteArrayOutputStream()

    val sourceImage = ImageIO.read(is)
    val thumbnail = sourceImage.getScaledInstance(200, -1, Image.SCALE_SMOOTH)
    val bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
      thumbnail.getHeight(null),
      BufferedImage.TYPE_INT_RGB)
    bufferedThumbnail.getGraphics.drawImage(thumbnail, 0, 0, null)
    ImageIO.write(bufferedThumbnail, "jpeg", os)
    os.toByteArray
  }

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