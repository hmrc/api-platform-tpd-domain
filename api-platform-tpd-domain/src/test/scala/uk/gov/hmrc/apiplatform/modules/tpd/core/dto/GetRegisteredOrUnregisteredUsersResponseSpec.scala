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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddress, UserId}
import uk.gov.hmrc.apiplatform.modules.common.utils._

class GetRegisteredOrUnregisteredUsersResponseSpec extends BaseJsonFormattersSpec {

  "GetRegisteredOrUnregisteredUserResponse JsonFormatters" when {
    val userId          = UserId.random
    val email           = LaxEmailAddress("bob@example.com")
    val exampleUser     = RegisteredOrUnregisteredUser(userId, email, true)
    val exampleResponse = GetRegisteredOrUnregisteredUsersResponse(List(exampleUser))
    val jsonText        = s"""{"users":[{"userId":"$userId","email":"${email.text}","isRegistered":true}]}"""

    "given an GetRegisteredOrUnregisteredUserResponse" should {
      "produce Json" in {
        Json.toJson(exampleResponse) shouldBe Json.parse(jsonText)
      }

      "read json" in {
        testFromJson[GetRegisteredOrUnregisteredUsersResponse](jsonText)(exampleResponse)
      }
    }
  }
}
