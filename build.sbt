organization in ThisBuild := "org.jetbrains"

version in ThisBuild := "0.3.9"

scalaVersion in ThisBuild := "2.12.8"

ideaBuild in ThisBuild := "193.3519.25"

ideaPluginName in ThisBuild := "library-test-ijext"

lazy val library = project.in(file("library")).settings(
  name := "library-test",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val support = project.in(file("ijext")).dependsOn(library).settings(
  name := "library-test-ijext",
  ideaInternalPlugins += "java",
  ideaExternalPlugins += "org.intellij.scala".toPlugin
).aggregate(library).enablePlugins(SbtIdeaPlugin)

lazy val testProject = project.in(file("test-project"))
  .settings(
//    uncomment and reimport project after running support/publishLocal
//    libraryDependencies += "org.jetbrains" %% "library-test" % (version in ThisBuild).value
  )