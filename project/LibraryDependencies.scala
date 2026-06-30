import sbt._

object LibraryDependencies {
  def apply(scalaVersion: String) = compileDependencies(scalaVersion) ++ testDependencies(scalaVersion)

  lazy val commonDomainVersion = "1.1.0"
  
  def tpdDomainDeps(scalaVersion: String) = compileDependencies(scalaVersion) ++ testDependencies(scalaVersion).map(_ % "test")

  def tpdTestDomainDeps(scalaVersion: String) = compileDependencies(scalaVersion) ++ testDependencies(scalaVersion)
  
  def compileDependencies(scalaVersion: String) = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion
  )

  def testDependencies(scalaVersion: String) = Seq (
    "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures" % commonDomainVersion
  )
}
