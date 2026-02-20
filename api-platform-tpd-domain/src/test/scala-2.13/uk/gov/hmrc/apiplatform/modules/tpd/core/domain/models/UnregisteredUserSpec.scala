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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddressFixtures, UserId}
import uk.gov.hmrc.apiplatform.modules.common.utils._

class UnregisteredUserSpec extends BaseJsonFormattersSpec with FixedClock with LaxEmailAddressFixtures {
  private val userId  = UserId.random
  private val example = UnregisteredUser(email = emailOne, creationTime = instant, userId = userId)

  "UnregisteredUser JsonFormatters" when {

    "given an empty UnregisteredUser" should {
      "produce Json" in {
        testToJson[UnregisteredUser](example)(
          ("email"        -> emailOne.text),
          ("creationTime" -> nowAsText),
          ("userId"       -> userId.toString())
        )
      }

      "read json" in {
        testFromJson[UnregisteredUser](s"""{"email":"$emailOne","creationTime":"$nowAsText","userId":"$userId"}""")(example)
      }
    }
  }
}
