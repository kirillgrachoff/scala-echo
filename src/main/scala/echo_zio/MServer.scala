package echo_zio

import zio.*
import zio.http.*
import zio.http.model.Method

object MServer extends ZIOAppDefault {
  private val app: HttpApp[Any, Nothing] = Http.collect[Request] {
    case Method.GET -> !! / text => Response.text(text)
  }

  def run = {
    Server.serve(app).provide(Server.default)
  }
}
