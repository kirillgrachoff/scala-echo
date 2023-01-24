ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

val Http4sVersion = "0.23.18"

lazy val root = (project in file("."))
  .settings(
    name := "AsyncEcho",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.4.5",
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
    ),
  )
