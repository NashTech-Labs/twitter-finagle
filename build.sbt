name := "twitter-finagle"

version := "0.1"

scalaVersion := "2.12.4"

//Finagle
libraryDependencies += "com.twitter" %% "finagle-core" % "6.44.0"
libraryDependencies += "com.twitter" %% "finagle-http" % "6.44.0"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0-SNAP9" % Test