import sbt._

object LibraryDependencies {
  lazy val commonDomainVersion = "0.14.0"
  
  lazy val tpdDomainDeps = compileDependencies ++ testCommonDependencies.map(_ % "test") ++ testDependencies.map(_ % "test")

  lazy val tpdTestDomainDeps = compileDependencies ++ testCommonDependencies
  
  lazy val compileDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion,
    "com.typesafe.play"       %% "play-json"                      % "2.9.3",
    "uk.gov.hmrc"             %% "play-json-union-formatter"      % "1.18.0-play-28",
    "org.typelevel"           %% "cats-core"                      % "2.10.0"
  )

  lazy val testCommonDependencies = Seq (
    "uk.gov.hmrc"             %% "api-platform-test-common-domain" % commonDomainVersion,
  )

  lazy val testDependencies = Seq[ModuleID]()
}
