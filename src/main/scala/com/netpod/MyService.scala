package com.netpod

import akka.actor.Actor
import com.netpod.domain.Customer
import spray.httpx.unmarshalling.Unmarshaller
import spray.routing
import spray.routing._
import spray.http._
import MediaTypes._

import scala.concurrent.Future

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  def read[T](s: String) = {}

  val myRoute =
    path("customer") {
        get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }

//  protected def postRoute: Route = {
//    entity[Customer](as[Customer]) { customer =>
//      complete ( "OK")
//    }
//  }


//
//    entity(Unmarshaller(MediaTypes.`application/json`) {
//      case httpEntity: HttpEntity => read[Customer](httpEntity.asString(HttpCharsets.`UTF-8`))
//    }) {
//      customer: Customer =>
//        ctx: RequestContext =>
//          handleRequest(ctx, StatusCodes.Created) {
//            log.debug("Creating customer: %s".format(customer))
//            customerService.create(customer)
//          }
//    }

}