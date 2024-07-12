/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.AccountSetupRequest

object AccountSetupRequestSpec {
  val example = AccountSetupRequest(
    roles = Some(List("r1")),
    rolesOther = Some("ro1"),
    services = Some(List("s1")),
    servicesOther = Some("so1"),
    targets = Some(List("t1")),
    targetsOther = Some("to1"),
    incomplete = Some(false)
  )

  val jsonText = """{"roles":["r1"],"rolesOther":"ro1","services":["s1"],"servicesOther":"so1","targets":["t1"],"targetsOther":"to1","incomplete":false}"""
}

class AccountSetupRequestSpec extends BaseJsonFormattersSpec {
  import AccountSetupRequestSpec._

  "AccountSetupRequest JsonFormatters" when {

    "given an AccountSetupRequest" should {
      "produce Json" in {
        testToJsonValues[AccountSetupRequest](example)(
          ("roles" -> JsArray(Seq(JsString("r1")))),
          ("rolesOther" -> JsString("ro1")),
          ("services" -> JsArray(Seq(JsString("s1")))),
          ("servicesOther" -> JsString("so1")),
          ("targets" -> JsArray(Seq(JsString("t1")))),
          ("targetsOther" -> JsString("to1")),
          ("incomplete" -> JsBoolean(false))
        )
      }

      "read json" in {
        testFromJson[AccountSetupRequest](jsonText)(example)
      }
    }
  }
}
