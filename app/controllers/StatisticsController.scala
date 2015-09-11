package controllers

import actors.{UserActor, MyWebSocketActor}
import play.api.libs.json.JsValue
import play.api.mvc.{WebSocket, Action, Controller}
import services.RedisService

import play.api.Play.current

import scala.concurrent.Future


object StatisticsController extends Controller {

  def incrementClicks(id: String) = Action {
    RedisService.increment(id, "clicks")
    NoContent
      //allows CORS
      .withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")
  }

  def ws = WebSocket.tryAcceptWithActor[JsValue, JsValue] { implicit request =>
    Future.successful(Right(UserActor.props("")))
  }

}
