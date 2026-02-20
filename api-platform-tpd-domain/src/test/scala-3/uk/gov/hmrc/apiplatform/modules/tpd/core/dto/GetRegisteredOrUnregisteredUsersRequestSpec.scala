/*
 * Copyright 2025 HM Revenue & Customs
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

import play.api.libs.json.Json

import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId
import uk.gov.hmrc.apiplatform.modules.common.utils._

class GetRegisteredOrUnregisteredUsersRequestSpec extends BaseJsonFormattersSpec {

  "GetRegisteredOrUnregisteredUserRequest JsonFormatters" when {
    val userId1        = UserId.random
    val userId2        = UserId.random
    val exampleRequest = GetRegisteredOrUnregisteredUsersRequest(List(userId1, userId2))
    val jsonText       = s"""{"userIds":["$userId1","$userId2"]}"""

    "given an GetRegisteredOrUnregisteredUserRequest" should {
      "produce Json" in {
        Json.toJson(exampleRequest) shouldBe Json.parse(jsonText)
      }

      "read json" in {
        testFromJson[GetRegisteredOrUnregisteredUsersRequest](jsonText)(exampleRequest)
      }
    }
  }
}
