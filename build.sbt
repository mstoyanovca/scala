name := "scala"
version := "0.1"
scalaVersion := "2.12.6"
val scalaCheckVersion = "1.14.0"
val circeVersion = "0.9.3"

scalacOptions ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
