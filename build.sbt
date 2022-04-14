name := "Team1417-Part2"
organization := "Team1417"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"


libraryDependencies ++= Seq(
  cacheApi
)

libraryDependencies ++= Seq(
  caffeine
)

lazy val akkaVersion = "2.6.14"
lazy val akkaHttpVersion = "10.1.14"

libraryDependencies += guice
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
libraryDependencies += "org.projectlombok" % "lombok" % "1.18.8" % "provided"
libraryDependencies += "org.mockito" % "mockito-core" % "3.12.4" % "test"
libraryDependencies += "junit" % "junit" % "4.12"

PlayKeys.devSettings += "play.server.http.idleTimeout" -> "infinite"
