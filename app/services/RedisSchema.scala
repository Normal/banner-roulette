package services

object RedisSchema {

  def views(id: String) = s"uid:$id:views"

  def clicks(id: String) = s"uid:$id:clicks"

  def conversions(id: String) = s"uid:$id:conversions"

  def all(id: String): List[String] = {
    List(views(id), clicks(id), conversions(id))
  }
}
