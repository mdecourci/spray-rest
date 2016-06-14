import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._
import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.github.retronym.SbtOneJar._
import spray.revolver.RevolverPlugin._

packageArchetype.java_application


resolvers += "spray repo" at "http://repo.spray.io"

oneJarSettings

organization  := "com.netpod"

version       := "0.1"

scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies ++= {
  val akkaVersion = "2.3.9"
  val sprayVersion = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayVersion,
    "io.spray"            %%  "spray-routing" % sprayVersion,
    "io.spray"            %%  "spray-client" % sprayVersion,
    "io.spray"            %%  "spray-testkit" % sprayVersion  % "test",
    "io.spray"            %%  "spray-json" % "1.3.2",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaVersion,
    "com.typesafe.akka"   %%  "akka-slf4j" % akkaVersion,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaVersion   % "test",
    "ch.qos.logback"      %   "logback-classic" % "1.1.3",
    "com.typesafe.slick"  %%  "slick" % "3.0.0",
    "com.h2database"               % "h2" % "1.4.187",
    "org.scalatest"       %%  "scalatest" % "2.2.5" % "test"
  )
}

Revolver.settings
