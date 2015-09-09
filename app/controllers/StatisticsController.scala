package controllers

import play.api.mvc.{Action, Controller}
import services.RedisService

object StatisticsController extends Controller {

  def incrementClicks(id: String) = Action {
    RedisService.increment(id, "clicks")
    NoContent
      //allows CORS
      .withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")
  }

}
