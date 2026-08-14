import sbt._

object LibraryDependencies {
  lazy val commonDomainVersion = "1.3.0"
  
  def domain(scalaVersion: String) =
    compileDependencies ++
    fixturesDependencies.map(_ % "test") ++ 
    commonTestDependencies(scalaVersion)

  def fixtures(scalaVersion: String) =
    compileDependencies ++
    fixturesDependencies.map(_ % "provided") ++ 
    commonTestDependencies(scalaVersion)

  def tests(scalaVersion: String) =
    compileDependencies ++
    fixturesDependencies.map(_ % "test") ++ 
    commonTestDependencies(scalaVersion)

  private def compileDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion % "provided"
  )

  private def fixturesDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures" % commonDomainVersion
  )

  def commonTestDependencies(scalaVersion: String) = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain-fixtures"  % commonDomainVersion
  ).map(_ % "test")
}
