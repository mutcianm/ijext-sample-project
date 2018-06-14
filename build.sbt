organization in ThisBuild := "org.jetbrains"

version in ThisBuild := "0.3.3"

scalaVersion in ThisBuild := "2.12.6"

ideaBuild := "182.2949.4"

lazy val root = project.in(file(".")).settings(
  name := "librarymanager-test",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)


lazy val support = project.in(file("ijext")).dependsOn(root).settings(
  name := "librarymanager-test-ijext",
  crossPaths := false,
  unmanagedJars in Compile += file("/home/miha/IdeaProjects/scala-plugin-for-ultimate/target/scala-2.12/scalaultimate_2.12-0.1-SNAPSHOT.jar")
).aggregate(root)

lazy val testProject = project.in(file("test-project")).settings(
  libraryDependencies += "org.jetbrains" %% "librarymanager-test" % "0.3.2"
)