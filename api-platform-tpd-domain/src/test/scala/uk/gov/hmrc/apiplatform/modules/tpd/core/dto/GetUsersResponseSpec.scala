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
import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.User
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.{EmailPreferences, EmailPreferencesSpec}

class GetUsersResponseSpec extends BaseJsonFormattersSpec with FixedClock {

  "GetUserResponse JsonFormatters" when {
    val userId          = UserId.random
    val email           = LaxEmailAddress("bob@example.com")
    val exampleUser     = User(
      email = email,
      firstName = "Bob",
      lastName = "Bobbins",
      registrationTime = Instants.aYearAgo,
      lastModified = instant,
      verified = true,
      accountSetup = None,
      mfaDetails = List.empty,
      nonce = None,
      emailPreferences = EmailPreferences.noPreferences,
      userId = userId
    )
    val exampleResponse = GetUsersResponse(List(exampleUser))
    val jsonText        =
      s"""{"users":[{"email":"$email","firstName":"Bob","lastName":"Bobbins","registrationTime":"${Texts.aYearAgo}","lastModified":"$nowAsText","verified":true,"mfaDetails":[],"emailPreferences":${EmailPreferencesSpec.jsonText},"userId":"$userId","failedLogins":0}]}"""

    "given an GetUserResponse" should {
      "produce Json" in {
        Json.toJson(exampleResponse) shouldBe Json.parse(jsonText)
      }

      "read json" in {
        testFromJson[GetUsersResponse](jsonText)(exampleResponse)
      }
    }
  }
}
