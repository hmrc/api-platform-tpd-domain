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

package uk.gov.hmrc.apiplatform.modules.tpd.core.dto

import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.PasswordChangeRequest

class PasswordChangeRequestSpec extends BaseJsonFormattersSpec {

  "PasswordChangeRequest JsonFormatters" when {
    val example = PasswordChangeRequest(email = LaxEmailAddressData.emailA, oldPassword = "pwdOld", newPassword = "pwdNew")

    "given an PasswordChangeRequest" should {
      "produce Json" in {
        testToJson[PasswordChangeRequest](example)(
          ("email"       -> example.email.text),
          ("oldPassword" -> "pwdOld"),
          ("newPassword" -> "pwdNew")
        )
      }

      "read json" in {
        testFromJson[PasswordChangeRequest](s"""{"email":"${LaxEmailAddressData.emailA}","oldPassword":"pwdOld","newPassword":"pwdNew"}""")(example)
      }
    }
  }
}
