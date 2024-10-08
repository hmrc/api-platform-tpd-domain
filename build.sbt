import scoverage.ScoverageKeys
import sbt._
import sbt.Keys._
import uk.gov.hmrc.DefaultBuildSettings.targetJvm
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import bloop.integrations.sbt.BloopDefaults

val appName = "api-platform-tpd-domain"

val scala2_13 = "2.13.12"

ThisBuild / majorVersion     := 0
ThisBuild / isPublicArtefact := true
ThisBuild / scalaVersion     := scala2_13

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

lazy val library = (project in file("."))
  .settings(publish / skip := true)
  .aggregate(
   apiPlatformTpdDomain, apiPlatformTestTpdDomain
  )

lazy val apiPlatformTpdDomain = Project("api-platform-tpd-domain", file("api-platform-tpd-domain"))
  .settings(
    libraryDependencies ++= LibraryDependencies.tpdDomainDeps,
    ScoverageSettings(),
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-eT"),

    // Compile / unmanagedSourceDirectories += baseDirectory.value / ".." / "common" / "src" / "main" / "scala",
    // Test / unmanagedSourceDirectories += baseDirectory.value / ".." / "common" / "src" / "test" / "scala",
    
    // Test / unmanagedSourceDirectories += baseDirectory.value / ".." / "test-common" / "src" / "main" / "scala"
  )
  .disablePlugins(JUnitXmlReportPlugin)

lazy val apiPlatformTestTpdDomain = Project("api-platform-test-tpd-domain", file("api-platform-test-tpd-domain"))
  .dependsOn(
    apiPlatformTpdDomain
  )
  .settings(
    libraryDependencies ++= LibraryDependencies.tpdTestDomainDeps,
    ScoverageKeys.coverageEnabled := false,
    // Compile / unmanagedSourceDirectories += baseDirectory.value / ".." / "common" / "src" / "main" / "scala",
    // Compile / unmanagedSourceDirectories += baseDirectory.value / ".." / "test-common" / "src" / "main" / "scala"
  )
  .disablePlugins(JUnitXmlReportPlugin)


commands ++= Seq(
  Command.command("run-all-tests") { state => "test" :: state },

  Command.command("clean-and-test") { state => "clean" :: "compile" :: "run-all-tests" :: state },

  // Coverage does not need compile !
  Command.command("pre-commit") { state => "clean" :: "scalafmtAll" :: "scalafixAll" :: "coverage" :: "run-all-tests" :: "coverageAggregate" :: "coverageOff" :: state }
)

Global / bloopAggregateSourceDependencies := true
