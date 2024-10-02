import sbt._

object LibraryDependencies {
  lazy val commonDomainVersion = "0.17.0"
  
  lazy val tpdDomainDeps = compileDependencies ++ testCommonDependencies.map(_ % "test") ++ testDependencies.map(_ % "test")

  lazy val tpdTestDomainDeps = compileDependencies ++ testCommonDependencies
  
  lazy val compileDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion
  )

  lazy val testCommonDependencies = Seq (
    "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures" % commonDomainVersion
  )

  lazy val testDependencies = Seq[ModuleID]()
}
