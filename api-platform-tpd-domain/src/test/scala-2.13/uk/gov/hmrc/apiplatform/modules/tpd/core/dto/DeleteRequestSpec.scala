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

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressFixtures
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.DeleteRequest

class DeleteRequestSpec extends BaseJsonFormattersSpec with LaxEmailAddressFixtures {

  "DeleteRequest JsonFormatters" when {
    val example = DeleteRequest(gatekeeperUserId = Some("123"), emailAddress = emailOne)

    "given a DeleteRequest" should {
      "produce Json" in {
        testToJsonValues[DeleteRequest](example)(
          ("gatekeeperUserId" -> JsString("123")),
          ("emailAddress"     -> JsString(example.emailAddress.text))
        )
      }

      "read json" in {
        testFromJson[DeleteRequest](s"""{"gatekeeperUserId":"123","emailAddress":"$emailOne"}""")(example)
      }
    }
  }
}
