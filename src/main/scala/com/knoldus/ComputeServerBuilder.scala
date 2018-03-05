package com.knoldus

import java.net.InetSocketAddress
import com.twitter.finagle.Http
import com.twitter.finagle.builder.{ Server, ServerBuilder }

class ComputeServerBuilder {

  val response = new ComputeResponse
  val address = new InetSocketAddress(10000)

  def start: Server = ServerBuilder()
    .stack(Http.server)
    .bindTo(address)
      .name("HttpServer")
    .build(response)

}
