package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import akka.actor.ActorRef
import akka.actor.Props

/**
 * Communicates between master and client.
 * One object per client.
 */
class SlaveActor(uid: String, board: ActorRef, out: ActorRef) extends Actor with ActorLogging {

  override def preStart() = {
    MasterActor() ! Subscribe
  }

  def receive = LoggingReceive {
    //Serialize master message and send to client
    case Message(muid, id, incr) if sender == board => {
      val js = Json.obj("type" -> "message", "uid" -> muid, "id" -> id, "incr" -> incr)
      out ! js
    }
      //deserialize client message and send to master
    case js: JsValue => board ! Message(uid, (js \ "id").as[String], (js \ "incr").as[String])
    case other => log.error("unhandled: " + other)
  }
}

object SlaveActor {
  def props(uid: String)(out: ActorRef) = Props(new SlaveActor(uid, MasterActor(), out))
}
