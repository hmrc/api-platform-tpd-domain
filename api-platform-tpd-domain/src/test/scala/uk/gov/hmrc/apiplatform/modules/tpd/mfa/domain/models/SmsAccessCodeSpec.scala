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

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.SmsAccessCode
import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaId

class SmsAccessCodeSpec extends BaseJsonFormattersSpec {
  import SmsAccessCodeSpec._

  "SmsAccessCode JsonFormatters" when {

    "given an SmsAccessCode" should {
      "produce Json" in {
        testToJsonValues[SmsAccessCode](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[SmsAccessCode](jsonText)(example)
      }
    }
  }
}

object SmsAccessCodeSpec extends FixedClock {
  //userId: UserId, mfaId: MfaId, accessCode: String, createdTime: Instant)
  val example = SmsAccessCode(userId = UserId.random, mfaId = MfaId.random, accessCode = "SOMECODE", createdTime = instant)

  val jsonObject = JsObject(Seq(
    ("userId" -> JsString(example.userId.toString)),
    ("mfaId" -> JsString(example.mfaId.toString)),
    ("accessCode" -> JsString(example.accessCode)),
    ("createdTime" -> JsString(nowAsText)),
  ))

  val jsonText = s"""{"userId":"${example.userId.toString}","mfaId":"${example.mfaId.toString}","accessCode":"${example.accessCode.toString}","createdTime":"$nowAsText"}"""
}