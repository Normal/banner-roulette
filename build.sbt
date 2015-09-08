import play.PlayScala

name := "banner-roulette"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "bootstrap" % "3.3.5",
  "com.livestream" %% "scredis" % "2.0.6",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "com.h2database" % "h2" % "1.3.175",
  "com.zaxxer" % "HikariCP-java6" % "2.3.3",
  "org.apache.commons" % "commons-io" % "1.3.2"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

//for scredis
resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"
