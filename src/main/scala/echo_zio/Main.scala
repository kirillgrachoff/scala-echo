package echo_zio

import zio.*

import scala.concurrent.duration.*

object Main extends ZIOAppDefault {
  def run = {
    val sleep = ZIO.sleep(1.second)
    val task = for {
      _ <- sleep
      _ <- Console.printLine("inside")
    } yield ()

    val repeatedTask = for {
      _ <- task.fork.repeatN(3).awaitAllChildren
    } yield ()

    repeatedTask.forever
  }
}
