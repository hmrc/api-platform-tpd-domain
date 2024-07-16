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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddress, LaxEmailAddressData}
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.EmailIdentifier

class EmailIdentifierSpec extends BaseJsonFormattersSpec {

  "EmailIdentifier JsonFormatters" when {
    val example = EmailIdentifier(email = LaxEmailAddressData.emailA)

    "given an EmailIdentifier" should {
      "produce Json" in {
        testToJson[EmailIdentifier](example)(
          ("email" -> example.email.text)
        )
      }

      "read json" in {
        testFromJson[EmailIdentifier](s"""{"email":"${LaxEmailAddressData.emailA.text}"}""")(example)
      }

      "parse text correctly" in {
        EmailIdentifier.parse("invalid") shouldBe None
        EmailIdentifier.parse("A@A.com") shouldBe Some(EmailIdentifier(LaxEmailAddress("a@a.com")))
      }
    }
  }
}
