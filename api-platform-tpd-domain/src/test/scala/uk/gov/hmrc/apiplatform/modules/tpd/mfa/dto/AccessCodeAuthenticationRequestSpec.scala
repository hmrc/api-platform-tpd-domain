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

package uk.gov.hmrc.apiplatform.modules.tpd.mfa.dto

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.dto.AccessCodeAuthenticationRequest
import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaId

class AccessCodeAuthenticationRequestSpec extends BaseJsonFormattersSpec {
  private val example = AccessCodeAuthenticationRequest(email = LaxEmailAddressData.emailA, accessCode = "123456", nonce = "aNonce", mfaId = MfaId.random)

  private val jsonText = s"""{"email":"${example.email.text}","accessCode":"123456","nonce":"aNonce","mfaId":"${example.mfaId.toString()}"}"""

  "AccessCodeAuthenticationRequest JsonFormatters" when {

    "given an AccessCodeAuthenticationRequest" should {
      "produce Json" in {
        testToJsonValues[AccessCodeAuthenticationRequest](example)(
          ("email" -> JsString(example.email.text)),
          ("accessCode" -> JsString(example.accessCode)),
          ("nonce" -> JsString(example.nonce)),
          ("mfaId" -> JsString(example.mfaId.toString()))
        )
      }

      "read json" in {
        testFromJson[AccessCodeAuthenticationRequest](jsonText)(example)
      }
    }
  }
}