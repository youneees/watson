name := "watson"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  // sksamuel elastic 4 scala
  "com.sksamuel.elastic4s" %% "elastic4s-core" % "5.3.0",
  "com.sksamuel.elastic4s" %% "elastic4s-tcp" % "5.3.0",

  // play json
  "com.typesafe.play" % "play-json_2.12" % "2.6.0-M6",

  // ibm watson apis
  "com.ibm.watson.developer_cloud" % "java-sdk" % "3.7.2",

  // scala test
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
