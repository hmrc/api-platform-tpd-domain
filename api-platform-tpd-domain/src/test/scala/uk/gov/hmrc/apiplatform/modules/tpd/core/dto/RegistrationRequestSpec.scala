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

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.RegistrationRequest

class RegistrationRequestSpec extends BaseJsonFormattersSpec {

  "RegistrationRequest JsonFormatters" when {
    val example = RegistrationRequest(email = LaxEmailAddressData.emailA, password = "pwd123", firstName = "Bob", lastName = "Bobbins", organisation = None)

    "given an RegistrationRequest" should {
      "produce Json" in {
        testToJson[RegistrationRequest](example)(
          ("email" -> example.email.text),
          ("password" -> "pwd123"),
          ("firstName" -> "Bob"),
          ("lastName" -> "Bobbins"),
        )
      }

      "read json" in {
        testFromJson[RegistrationRequest](s"""{"email":"${LaxEmailAddressData.emailA.text}","password":"pwd123","firstName":"Bob","lastName":"Bobbins"}""")(example)
      }
    }
  }
}
