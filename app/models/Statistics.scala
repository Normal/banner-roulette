package models

/**
 * Campaign statistics. Represents some dynamic info about campaign.
 */
case class Statistics(id: Option[Long] = None, views: Long, clicks: Long, conversions: Long) {

}
