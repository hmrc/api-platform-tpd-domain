file://<WORKSPACE>/api-platform-tpd-domain/src/main/scala/uk/gov/hmrc/apiplatform/modules/tpd/core/domain/models/AccountSetup.scala
### scala.reflect.internal.FatalError: no context found for source-file://<WORKSPACE>/api-platform-tpd-domain/src/main/scala/uk/gov/hmrc/apiplatform/modules/tpd/core/domain/models/AccountSetup.scala,line-12,offset=449

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>/.bloop/api-platform-tpd-domain/bloop-bsp-clients-classes/classes-Metals-gM0xio75RNSyc3AwVbuf_Q== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.10/semanticdb-javac-0.9.10.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/artefacts.tax.service.gov.uk/artifactory/hmrc-releases/uk/gov/hmrc/api-platform-common-domain_2.13/0.13.0/api-platform-common-domain_2.13-0.13.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-json_2.13/2.9.3/play-json_2.13-2.9.3.jar [exists ], <HOME>/.cache/coursier/v1/https/artefacts.tax.service.gov.uk/artifactory/hmrc-releases/uk/gov/hmrc/play-json-union-formatter_2.13/1.21.0/play-json-union-formatter_2.13-1.21.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_2.13/2.10.0/cats-core_2.13-2.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/playframework/play-json_2.13/3.0.1/play-json_2.13-3.0.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/play/play-functional_2.13/2.9.3/play-functional_2.13-2.9.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.14.3/jackson-core-2.14.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.14.3/jackson-annotations-2.14.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.14.3/jackson-datatype-jdk8-2.14.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.14.3/jackson-datatype-jsr310-2.14.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.14.3/jackson-databind-2.14.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_2.13/2.10.0/cats-kernel_2.13-2.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/playframework/play-functional_2.13/3.0.1/play-functional_2.13-3.0.1.jar [exists ]
Options:
-unchecked -deprecation -Xlint -encoding UTF-8 -Ywarn-macros:after -release 8 -Yrangepos -Xplugin-require:semanticdb


action parameters:
offset: 449
uri: file://<WORKSPACE>/api-platform-tpd-domain/src/main/scala/uk/gov/hmrc/apiplatform/modules/tpd/core/domain/models/AccountSetup.scala
text:
```scala
/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDI@@TIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import play.api.libs.json.{Json, OFormat}

case class AccountSetup(
    roles: List[String] = List.empty,
    rolesOther: Option[String] = None,
    services: List[String] = List.empty,
    servicesOther: Option[String] = None,
    targets: List[String] = List.empty,
    targetsOther: Option[String] = None,
    incomplete: Boolean = true
  )

object AccountSetup {
  implicit val format: OFormat[AccountSetup] = Json.format[AccountSetup]
}

```



#### Error stacktrace:

```
scala.tools.nsc.interactive.CompilerControl.$anonfun$doLocateContext$1(CompilerControl.scala:100)
	scala.tools.nsc.interactive.CompilerControl.doLocateContext(CompilerControl.scala:100)
	scala.tools.nsc.interactive.CompilerControl.doLocateContext$(CompilerControl.scala:99)
	scala.tools.nsc.interactive.Global.doLocateContext(Global.scala:114)
	scala.meta.internal.pc.PcDefinitionProvider.definitionTypedTreeAt(PcDefinitionProvider.scala:151)
	scala.meta.internal.pc.PcDefinitionProvider.definition(PcDefinitionProvider.scala:68)
	scala.meta.internal.pc.PcDefinitionProvider.definition(PcDefinitionProvider.scala:16)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$definition$1(ScalaPresentationCompiler.scala:386)
```
#### Short summary: 

scala.reflect.internal.FatalError: no context found for source-file://<WORKSPACE>/api-platform-tpd-domain/src/main/scala/uk/gov/hmrc/apiplatform/modules/tpd/core/domain/models/AccountSetup.scala,line-12,offset=449