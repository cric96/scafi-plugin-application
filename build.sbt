name := "scafi-plugin-application"

val scafi_core  = "it.unibo.apice.scafiteam" %% "scafi-core"  % "0.3.2"

ThisBuild / scalaVersion := "2.11.8"
ThisBuild / organization := "sm.example"
lazy val pluginJar = "plugin/0.4.1.jar"
lazy val hello = (project in file("."))
  .settings(
    name := "Plugin Application",
    autoCompilerPlugins := true,
    scalacOptions += "-Xplugin:" + baseDirectory.value / pluginJar,
    libraryDependencies ++= Seq(scafi_core)
  )