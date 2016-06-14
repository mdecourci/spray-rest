package com.netpod

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import akka.io.IO
import com.typesafe.config.ConfigFactory
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

import scala.util.Try

import scala.util.Try

object Boot extends App {
  /** Application config object. */
  val config = ConfigFactory.load()

  /** Port to start service on. */
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

  /** User name used to access database. */
  lazy val dbUser = Try(config.getString("db.user")).toOption.orNull
  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("spray-api-service")
  val log = Logging(system, getClass)

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "spray-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 9080)
}
