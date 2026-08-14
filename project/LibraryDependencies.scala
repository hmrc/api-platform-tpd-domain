import sbt._

object LibraryDependencies {
  lazy val commonDomainVersion = "1.3.0"
  
  def tpdDomainDeps(scalaVersion: String) = compileDependencies(scalaVersion) ++ testDependencies(scalaVersion)

  def fixturesAndTestDeps(scalaVersion: String) = compileDependencies(scalaVersion) ++ testDependencies(scalaVersion, true)

  def compileDependencies(scalaVersion: String) = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion % "provided"
  )

  def testDependencies(scalaVersion: String, provided: Boolean = false) = Seq(
    if(provided)
      "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures"  % commonDomainVersion % "provided"
    else 
      "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures"  % commonDomainVersion % "test"
  )
}
