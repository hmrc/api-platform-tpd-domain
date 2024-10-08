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

package uk.gov.hmrc.apiplatform.modules.tpd.session.dto

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.session.domain.models.UserSessionSpec
import uk.gov.hmrc.apiplatform.modules.tpd.session.dto.UserAuthenticationResponse

class UserAuthenticationResponseSpec extends BaseJsonFormattersSpec with FixedClock {
  import UserAuthenticationResponseSpec._

  "UserAuthenticationResponse JsonFormatters" when {

    "given a UserAuthenticationResponse" should {
      "produce Json" in {
        testToJsonValues[UserAuthenticationResponse](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[UserAuthenticationResponse](UserAuthenticationResponseSpec.jsonText)(example)
      }
    }
  }
}

object UserAuthenticationResponseSpec extends FixedClock {

  val example = UserAuthenticationResponse(
    accessCodeRequired = true,
    mfaEnabled = true,
    nonce = Some("aTextValue"),
    session = Some(UserSessionSpec.example)
  )

  val jsonObject = JsObject(Seq(
    ("accessCodeRequired" -> JsBoolean(true)),
    ("mfaEnabled"         -> JsBoolean(true)),
    ("nonce"              -> JsString("aTextValue")),
    ("session"            -> UserSessionSpec.jsonObject)
  ))

  val jsonText =
    s"""{"accessCodeRequired":true,"mfaEnabled":true,"nonce":"aTextValue","session":${UserSessionSpec.jsonText}}"""
}
