package com.knoldus

import com.knoldus.FinagleFutureConverter._
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http._
import com.twitter.finagle.{Http, Service}
import com.twitter.util.Closable
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

class ComputeResponseTest extends AsyncFlatSpec with BeforeAndAfterAll{

  var server: com.twitter.finagle.builder.Server = _
  var client: Service[Request, Response] = _
  var computeServerBuilder: ComputeServerBuilder = _

  it should "success the post request" in {
    val postRequest = Request(Version.Http11, Method.Post, "/")
    postRequest.setContentString("Knoldus")
    val postFutureResponse = client(postRequest).asScala
    postFutureResponse.map(response => println(response.getContentString()))
    postFutureResponse.map(response => assert(response.status === Status.Ok && response.contentString.contains("Knoldus")))
  }

  it should "fail the post request" in {
    val postRequest = Request(Version.Http11, Method.Post, "/badRequest")
    postRequest.setContentString("Knoldus")
    val postFutureResponse = client(postRequest).asScala
    postFutureResponse.map(response => assert(response.status === Status.NotFound))
  }

  it should "success the get request" in {
    val getRequest = Request(Version.Http11, Method.Get, "/")
    getRequest.setContentString("Finagle")
    val getFutureResponse= client(getRequest).asScala
    getFutureResponse.map(response => println(response.getContentString()))
    getFutureResponse.map(response => assert(response.status === Status.Ok && response.contentString.contains("Finagle")))
  }

  it should "fail the get request" in {
    val getRequest = Request(Version.Http11, Method.Get, "/badRequest")
    getRequest.setContentString("Finagle")
    val getFutureResponse= client(getRequest).asScala
    getFutureResponse.map(response => assert(response.status === Status.NotFound))
  }

  override def beforeAll(): Unit = {
    computeServerBuilder = new ComputeServerBuilder
    server = computeServerBuilder.start
    client = ClientBuilder()
      .stack(Http.client)
      .hosts(computeServerBuilder.address)
      .hostConnectionLimit(1)
      .build()
  }

  override def afterAll(): Unit = {
    Closable.all(server, client).close()
  }


}
