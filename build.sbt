import scala.tools.nsc.io.JFile

lazy val root = project
  .in(file("."))
  .settings(
    name := "voicekit-grpc",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "2.13.12",
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true) -> (Compile / sourceManaged).value / "scalapb",
      scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
    ),
    Compile / PB.includePaths ++= Seq(new JFile("third_party/googleapis/")),
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % "1.50.1",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "com.typesafe" % "config" % "1.4.3",
      "dev.zio" %% "zio-http" % "0.0.5",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.16.1",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.16.1",
      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
      "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf"
    )
  )
