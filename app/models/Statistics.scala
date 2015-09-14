package models

/**
 * Represents some dynamic info about campaign.
 * Despite views, clicks, conversions are numeric - more convenient to store them as strings.
 * Stored in redis.
 *
 * @param id campaign id to link one-to-one with Campaign object
 * @param views number of campaign views on client websites
 * @param clicks number of clicks
 * @param conversions clicks/views*100
 */
case class Statistics(id: Option[Long], views: String, clicks: String, conversions: String) {

  def this(id: Option[Long] = None, views: String, clicks: String) =
    this(id, views, clicks, if (views == "0") "0" else (clicks.toLong / views.toDouble * 100)
      //discard fraction
      .toLong
      .toString)

}

object Statistics {
  def apply(id: Option[Long] = None, views: String, clicks: String) = new Statistics(id, views, clicks)
}
