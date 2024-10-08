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

class AccountSetupSpec extends BaseJsonFormattersSpec {

  "AccountSetup JsonFormatters" when {
    val example = AccountSetup(roles = List("role1"))

    "given an empty AccountSetup" should {
      "produce Json" in {
        testToJsonValues[AccountSetup](example)(
          ("roles"      -> JsArray(Seq(JsString("role1")))),
          ("services"   -> JsArray()),
          ("targets"    -> JsArray()),
          ("incomplete" -> JsBoolean(true))
        )
      }

      "read json" in {
        testFromJson[AccountSetup]("""{"roles":["role1"],"services":[],"targets":[],"incomplete":true}""")(example)
      }
    }
  }
}
