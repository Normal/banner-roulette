package services

import scredis.Redis
import models.Statistics

object RedisService {

  //redis configuration can be passed here: Redis(config)
  lazy val redis = Redis()

  def get(campaignId: String): Statistics = {
    val values = redis.mGetAsMap[String](RedisSchema.all(campaignId):_*)

    Statistics(Some(1), 0, 0, 0)
  }

  def increment(campaignId: Long, key: String): Unit = {

  }
}
