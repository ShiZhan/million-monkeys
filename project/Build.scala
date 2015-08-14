import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object MyBuild extends Build {
  val scalaV = Option(System.getProperty("scala.version")).getOrElse("2.11.6")
  lazy val commonSettings = Defaults.defaultSettings ++ Seq(
    version := "0.1-SNAPSHOT",
    organization := "com.simba",
    scalaVersion := scalaV
  )

  lazy val buildSettings = commonSettings

  lazy val MillionMonkeys = Project(
    id = "MillionMonkeys",
    base = file("."),
    settings = commonSettings ++ assemblySettings ++
      Seq(
        logLevel in assembly := Level.Warn
      )
  )
}
