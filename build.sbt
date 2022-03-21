name := """Team1417"""
organization := "ConcordiaRYSY"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.17.5" % Test

