package models

/**
 * Represents some dynamic info about campaign.
 * Despite views, clicks, conversions are numeric - more convenient to store them as strings.
 * Stored in redis.
 *
 * @param id campaign id to link one-to-one with Campaign object
 * @param views number of campaign views on client websites
 * @param clicks number of clicks
 * @param conversions ???
 */
case class Statistics(id: Option[Long] = None, views: String, clicks: String, conversions: String)
