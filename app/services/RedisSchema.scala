package services

object RedisSchema {

  def views(id: Long) = s"uid:$id:views"

  def clicks(id: Long) = s"uid:$id:clicks"

  def conversions(id: Long) = s"uid:$id:conversions"

  def all(id: Long): List[String] = {
    List(views(id), clicks(id), conversions(id))
  }
}
