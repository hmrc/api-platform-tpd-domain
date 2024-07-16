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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.DeviceSessionId

class SessionCreateWithDeviceRequestSpec extends BaseJsonFormattersSpec with FixedClock {
  import SessionCreateWithDeviceRequestSpec._

  "SessionCreateWithDeviceRequest JsonFormatters" when {

    "given a SessionCreateWithDeviceRequest" should {
      "produce Json" in {
        testToJsonValues[SessionCreateWithDeviceRequest](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[SessionCreateWithDeviceRequest](SessionCreateWithDeviceRequestSpec.jsonText)(example)
      }
    }
  }
}

object SessionCreateWithDeviceRequestSpec extends FixedClock {

  val example = SessionCreateWithDeviceRequest(
    email = LaxEmailAddressData.emailA,
    password = "pwd123",
    mfaMandatedForUser = Some(true),
    deviceSessionId = Some(DeviceSessionId.random)
  )

  val devSesIdText = example.deviceSessionId.map(_.toString()).get

  val jsonObject = JsObject(Seq(
    ("email"              -> JsString(LaxEmailAddressData.emailA.text)),
    ("password"           -> JsString("pwd123")),
    ("mfaMandatedForUser" -> JsBoolean(true)),
    ("deviceSessionId"    -> JsString(devSesIdText))
  ))

  val jsonText =
    s"""{"email":"${LaxEmailAddressData.emailA.text}","password":"pwd123","mfaMandatedForUser":true,"deviceSessionId":"$devSesIdText"}"""

}
