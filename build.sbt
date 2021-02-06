name := "guardrail-sample-http4s-zio"
organization := "se.hardchee"

libraryDependencies ++= Seq(
  // Depend on http4s, which will pull in cats and circe
  "org.http4s"       %% "http4s-blaze-client"   % "0.21.18",
  "org.http4s"       %% "http4s-blaze-server"   % "0.21.18",
  "org.http4s"       %% "http4s-circe"          % "0.21.18",
  "org.http4s"       %% "http4s-dsl"            % "0.21.18",

  // ZIO and the interop library
  "dev.zio"          %% "zio"                   % "1.0.4-2",
  "dev.zio"          %% "zio-interop-cats"      % "2.2.0.1",
)

// Better syntax for dealing with partially-applied types
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full)

// Better semantics for for comprehensions
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

// Server config
guardrailTasks in Compile := List(
  ScalaServer(file("server.yaml"), pkg="example.server", framework="http4s"),
)

// Client config for tests
guardrailTasks in Test := List(
  ScalaClient(file("server.yaml"), pkg="example.client", framework="http4s"),
)
