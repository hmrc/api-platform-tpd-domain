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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressFixtures
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.PasswordResetRequest

class PasswordResetRequestSpec extends BaseJsonFormattersSpec with LaxEmailAddressFixtures {

  "PasswordResetRequest JsonFormatters" when {
    val example = PasswordResetRequest(email = emailOne, newPassword = "pwdNew")

    "given an PasswordResetRequest" should {
      "produce Json" in {
        testToJson[PasswordResetRequest](example)(
          ("email"       -> example.email.text),
          ("newPassword" -> "pwdNew")
        )
      }

      "read json" in {
        testFromJson[PasswordResetRequest](s"""{"email":"$emailOne","newPassword":"pwdNew"}""")(example)
      }
    }
  }
}
