import sbt._

object LibraryDependencies {
  lazy val commonDomainVersion = "0.18.0"
  
  lazy val tpdDomainDeps = compileDependencies ++ testDependencies.map(_ % "test")

  lazy val tpdTestDomainDeps = compileDependencies ++ testDependencies
  
  lazy val compileDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion
  )

  lazy val testDependencies = Seq (
    "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures" % commonDomainVersion
  )
}
