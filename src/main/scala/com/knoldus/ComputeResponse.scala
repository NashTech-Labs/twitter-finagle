package com.knoldus

import com.twitter.finagle.Service
import org.apache.log4j.LogManager
import com.twitter.finagle.http._
import com.twitter.util.Future

class ComputeResponse extends Service[Request, Response]{
  private val log = LogManager.getLogger(this.getClass)

  def apply(request: Request) = {
    request.method match {

      case Method.Post =>
        request.uri match {

          case "/" =>
            log.error("in post")
            val str = request.getContentString()
            //any business logic
            val response = Response(Version.Http11, Status.Ok)
            response.contentString = "Hello..!! " + str
            Future.value(response)

          case _ =>
            log.error("REQUEST NOT FOUND")
            Future.value(Response(Version.Http11, Status.NotFound))
        }

      case Method.Get =>
        request.uri match {

          case "/" =>
            val str = request.getContentString()
            //any business logic
            val response = Response(Version.Http11, Status.Ok)
            response.contentString = "Thank You " + str
            Future.value(response)

          case _ =>
            log.error("REQUEST NOT FOUND")
            Future.value(Response(Version.Http11, Status.NotFound))
        }
    }
  }
}