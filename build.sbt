name := "scala"
version := "3"
scalaVersion := "2.12.12"

val scalaTestVersion = "3.2.3"
val scalaCheckVersion = "1.14.0"
val circeVersion = "0.12.3"
val slickVersion = "3.3.2"
val slf4jVersion = "1.7.30"
val h2Version = "1.4.199"

scalacOptions ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "org.scalatest" %% "scalatest-freespec" % scalaTestVersion % "test",
  "org.scalatestplus" %% "scalacheck-1-14" % "3.2.2.0",

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "com.typesafe" % "config" % "1.4.1",

  "com.typesafe.slick" %% "slick" % slickVersion,
  "org.slf4j" % "slf4j-simple" % slf4jVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,

  "com.h2database" % "h2" % h2Version)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
