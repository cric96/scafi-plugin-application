name := "scafi-plugin-application"

val scafi_core  = "it.unibo.apice.scafiteam" %% "scafi-core"  % "0.3.2"

ThisBuild / scalaVersion := "2.11.8"
ThisBuild / organization := "sm.example"

lazy val hello = (project in file("."))
  .settings(
    name := "Plugin Application",
    libraryDependencies ++= Seq(scafi_core)
  )