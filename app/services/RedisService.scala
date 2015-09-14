package services

import scredis.Redis
import models.Statistics

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Redis operations for Statistics model.
 */
object RedisService {

  //redis configuration can be passed here: Redis(config)
  lazy val redis = Redis()

  // Import the internal ActorSystem's dispatcher (execution context) to register callbacks
  import redis.dispatcher

  def get(id: String): Statistics = {
    var statistics: Statistics = Statistics(Some(id.toLong), "0", "0")
    val result = Await.result(redis.mGetAsMap[String](RedisSchema.all(id): _*), Duration.Inf)
    Statistics(Some(id.toLong),
      result.getOrElse(RedisSchema.views(id), "0"),
      result.getOrElse(RedisSchema.clicks(id), "0"))
  }

  def increment(id: String, key: String): Unit = {
    redis.incr(RedisSchema.key(id, key))
  }
}

/**
 * Redis keys for Statistics model.
 */
object RedisSchema {

  def views(id: String) = s"uid:$id:views"

  def clicks(id: String) = s"uid:$id:clicks"

  def all(id: String): List[String] = {
    List(views(id), clicks(id))
  }

  def key(id: String, key:String) = {
    s"uid:$id:$key"
  }
}
