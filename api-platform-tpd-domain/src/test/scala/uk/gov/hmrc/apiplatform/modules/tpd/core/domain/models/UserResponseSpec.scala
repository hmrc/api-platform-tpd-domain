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

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddressData, UserId}
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.{EmailPreferences, EmailPreferencesSpec}
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.{MfaId, SmsMfaDetailResponse}

class UserResponseSpec extends BaseJsonFormattersSpec with FixedClock {
  import UserResponseSpec._

  "UserResponse JsonFormatters" when {

    "given a UserResponse" should {
      "produce Json" in {
        testToJsonValues[UserResponse](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[UserResponse](UserResponseSpec.jsonText)(example)
      }

      "read full json" in {
        testFromJson[UserResponse](fullJsonText)(fullExample)
      }

      "produce full json" in {
        Json.toJson(fullExample) shouldBe Json.parse(fullJsonText)
      }
    }
  }

  val fullExample = example.copy(
    mfaEnabled = true,
    mfaDetails = List(
      SmsMfaDetailResponse(
        id = mfaId,
        createdOn = instant,
        name = "xxx",
        mobileNumber = "07999123456",
        verified = true
      )
    ),
    emailPreferences = EmailPreferencesSpec.example
  )

  val fullJsonText =
    s"""{"email":"${LaxEmailAddressData.emailA.text}","firstName":"Bob","lastName":"Bobbins","registrationTime":"$nowAsText","lastModified":"$nowAsText","verified":true,"organisation":"Bobbers","mfaEnabled":true,"mfaDetails":[{"id":"$mfaId","name":"xxx","createdOn":"$nowAsText","mobileNumber":"07999123456","verified":true,"mfaType":"SMS"}],"emailPreferences":${EmailPreferencesSpec.jsonText},"userId":"$userId"}"""

}

object UserResponseSpec extends FixedClock {
  val mfaId  = MfaId.random
  val userId = UserId.random

  val example = UserResponse(
    email = LaxEmailAddressData.emailA,
    firstName = "Bob",
    lastName = "Bobbins",
    registrationTime = instant,
    lastModified = instant,
    verified = true,
    accountSetup = None,
    organisation = Some("Bobbers"),
    mfaEnabled = false,
    mfaDetails = List.empty,
    nonce = None,
    emailPreferences = EmailPreferences.noPreferences,
    userId = userId
  )

  val jsonObject = JsObject(Seq(
    ("userId"           -> JsString(userId.toString())),
    ("email"            -> JsString(LaxEmailAddressData.emailA.text)),
    ("firstName"        -> JsString("Bob")),
    ("lastName"         -> JsString("Bobbins")),
    ("registrationTime" -> JsString(nowAsText)),
    ("lastModified"     -> JsString(nowAsText)),
    ("verified"         -> JsBoolean(true)),
    ("organisation"     -> JsString("Bobbers")),
    ("mfaEnabled"       -> JsBoolean(false)),
    ("mfaDetails"       -> JsArray.empty),
    ("emailPreferences" -> EmailPreferencesSpec.jsonObject)
  ))

  val jsonText =
    s"""{"email":"${LaxEmailAddressData.emailA.text}","firstName":"Bob","lastName":"Bobbins","registrationTime":"$nowAsText","lastModified":"$nowAsText","verified":true,"organisation":"Bobbers","mfaEnabled":false,"mfaDetails":[],"emailPreferences":{"interests":[],"topics":[]},"userId":"$userId"}"""

}
