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

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaId

class RegisterSmsResponseSpec extends BaseJsonFormattersSpec {
  
  private val example = RegisterSmsResponse(mobileNumber = "07999123456", mfaId = MfaId.random)

  private val jsonText = s"""{"mobileNumber":"07999123456","mfaId":"${example.mfaId.toString}"}"""

  "RegisterSmsResponse JsonFormatters" when {

    "given an RegisterSmsResponse" should {
      "produce Json" in {
        testToJson[RegisterSmsResponse](example)(
          ( "mobileNumber" -> "07999123456"),
          ( "mfaId" -> example.mfaId.toString()),
        )
      }

      "read json" in {
        testFromJson[RegisterSmsResponse](jsonText)(example)
      }
    }
  }
}
