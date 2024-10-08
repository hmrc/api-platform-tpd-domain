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

package uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.{DeviceSession, DeviceSessionId}

class DeviceSessionSpec extends BaseJsonFormattersSpec {
  import DeviceSessionSpec._

  "DeviceSession JsonFormatters" when {

    "given an DeviceSession" should {
      "produce Json" in {
        testToJsonValues[DeviceSession](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[DeviceSession](jsonText)(example)
      }
    }
  }
}

object DeviceSessionSpec {
  val example = DeviceSession(deviceSessionId = DeviceSessionId.random, userId = UserId.random)

  val jsonObject = JsObject(Seq(
    ("deviceSessionId" -> JsString(example.deviceSessionId.toString)),
    ("userId"          -> JsString(example.userId.toString))
  ))

  val jsonText = s"""{"deviceSessionId":"${example.deviceSessionId.toString}","userId":"${example.userId.toString}"}"""
}
