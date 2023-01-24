import cats.effect.std.Semaphore
import cats.effect.{IO, IOApp}

import scala.concurrent.duration._

object Main extends IOApp.Simple {
  val run = for {
    semaphore <- Semaphore[IO](4)

    sleep = IO.sleep(1.second)

    poll = for {
      _ <- sleep
      _ <- IO.println("inside")
      _ <- semaphore.release
    } yield ()

    _ <- (semaphore.acquire >> poll.start).foreverM.void
  } yield ()
}