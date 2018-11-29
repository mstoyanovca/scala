name := "scala"
version := "0.1"
scalaVersion := "2.12.6"

val scalaTestVersion = "3.0.5"
val scalaCheckVersion = "1.14.0"
val circeVersion = "0.9.3"
val slickVersion = "3.2.3"
val slf4jVersion = "1.6.4"
val h2Version = "1.4.197"

scalacOptions ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "com.typesafe" % "config" % "1.3.3",

  "com.typesafe.slick" %% "slick" % slickVersion,
  "org.slf4j" % "slf4j-simple" % slf4jVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,

  "com.h2database" % "h2" % h2Version)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
