lazy val root = (project in file("."))
  .settings(
    name := "Lychee",
    scalaVersion := "2.12.1"
  )

val akkaVersion = "2.5.3"

val akkaHttpVersion = "10.0.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
)