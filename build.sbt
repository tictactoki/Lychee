lazy val root = (project in file("."))
  .settings(
    name := "Lychee",
    scalaVersion := "2.11.10"
  )

val akkaVersion = "2.5.3"

val akkaHttpVersion = "10.0.9"

fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.slf4j" % "slf4j-simple" % "1.7.12",
  "com.orientechnologies" % "orientdb-graphdb" % "2.2.4",
  "com.orientechnologies" % "orientdb-core" % "2.2.4",
  "com.orientechnologies" % "orientdb-client" % "2.2.4",
  "com.orientechnologies" % "orientdb-distributed" % "2.2.4"
)
