package controllers

import actors.SlaveActor
import play.api.libs.json.JsValue
import play.api.mvc.{WebSocket, Action, Controller}
import services.RedisService

import play.api.Play.current

import scala.concurrent.Future

/**
 * Handles special Statistics requests.
 */
object StatisticsController extends Controller {

  /**
   * Increments campaign clicks. Note: views increments in CampaignController.random.
   */
  def incrementClicks(id: String) = Action {
    RedisService.increment(id, "clicks")
    NoContent
      //allows CORS
      .withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")
  }

  /**
   * Create socket for real-time Statistics updating on UI.
   */
  def ws = WebSocket.tryAcceptWithActor[JsValue, JsValue] { implicit request =>
    Future.successful(Right(SlaveActor.props("")))
  }

}
