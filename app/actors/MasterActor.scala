package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import akka.actor.ActorRef
import akka.actor.Terminated
import play.libs.Akka
import akka.actor.Props

/**
 * Distributes messages between awaited clients on send event.
 * Adds client to subscribers on onmessage event.
 */
class MasterActor extends Actor with ActorLogging {
  var users = Set[ActorRef]()

  def receive = LoggingReceive {
    case m:Message => users map { _ ! m}
    case Subscribe => {
      users += sender
      context watch sender
    }
    case Terminated(user) => users -= user
  }
}

object MasterActor {
  lazy val master = Akka.system().actorOf(Props[MasterActor])
  def apply() = master
}

/**
 * Represents web-socket communication data model.
 */
case class Message(uuid: String, id: String, incr: String)

object Subscribe