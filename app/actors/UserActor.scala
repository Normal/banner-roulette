package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import akka.actor.ActorRef
import akka.actor.Props


class UserActor(uid: String, board: ActorRef, out: ActorRef) extends Actor with ActorLogging {


  override def preStart() = {
    BoardActor() ! Subscribe
  }

  def receive = LoggingReceive {
    case Message(muid, id, incr) if sender == board => {
      val js = Json.obj("type" -> "message", "uid" -> muid, "id" -> id, "incr" -> incr)
      out ! js
    }
    case js: JsValue => board ! Message(uid, (js \ "id").as[String], (js \ "incr").as[String])
    case other => log.error("unhandled: " + other)
  }
}

object UserActor {
  def props(uid: String)(out: ActorRef) = Props(new UserActor(uid, BoardActor(), out))
}
