name := "scala"
version := "0.1"
scalaVersion := "2.12.6"
val circeVersion = "0.9.3"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
