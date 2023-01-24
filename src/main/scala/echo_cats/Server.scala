package echo_cats

import cats.effect.{Async, ExitCode, IO, IOApp}
import cats.syntax.all.*
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*
import org.http4s.ember.server.*
import org.http4s.implicits.*
import org.http4s.server.Router
import org.http4s.server.middleware.Logger

import scala.concurrent.duration.*

object Server extends IOApp.Simple {

  val echoService = HttpRoutes.of[IO] {
    case GET -> Root / "echo" / text =>
      Ok(text)
  }.orNotFound

  val run = for {
    _ <- IO.println("initializing server")
    _ <- EmberServerBuilder.default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(Logger.httpApp(true, true)(echoService))
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
  } yield ()
}
