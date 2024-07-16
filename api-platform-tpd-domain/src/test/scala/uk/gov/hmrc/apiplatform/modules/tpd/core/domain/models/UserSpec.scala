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
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.{MfaId, SmsMfaDetail}

class UserSpec extends BaseJsonFormattersSpec with FixedClock {
  import UserSpec._

  "User JsonFormatters" when {

    "given a User" should {
      "produce Json" in {
        testToJsonValues[User](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[User](UserSpec.jsonText)(example)
      }

      "read full json" in {
        testFromJson[User](fullJsonText)(fullExample)
      }

      "produce full json" in {
        Json.toJson(fullExample) shouldBe Json.parse(fullJsonText)
      }
    }

    //TODO test displayName
    //TODO test hasVerifiedMfa
  }

  val fullExample = example.copy(
    mfaDetails = List(
      SmsMfaDetail(
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
    s"""{"email":"${LaxEmailAddressData.emailA.text}","firstName":"Bob","lastName":"Bobbins","registrationTime":"$nowAsText","lastModified":"$nowAsText","verified":true,"organisation":"Bobbers","mfaDetails":[{"id":"$mfaId","name":"xxx","createdOn":"$nowAsText","mobileNumber":"07999123456","verified":true,"mfaType":"SMS"}],"emailPreferences":${EmailPreferencesSpec.jsonText},"userId":"$userId"}"""

}

object UserSpec extends FixedClock {
  val mfaId  = MfaId.random
  val userId = UserId.random

  val example = User(
    email = LaxEmailAddressData.emailA,
    firstName = "Bob",
    lastName = "Bobbins",
    registrationTime = instant,
    lastModified = instant,
    verified = true,
    accountSetup = None,
    organisation = Some("Bobbers"),
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
    ("mfaDetails"       -> JsArray.empty),
    ("emailPreferences" -> EmailPreferencesSpec.jsonObject)
  ))

  val jsonText =
    s"""{"email":"${LaxEmailAddressData.emailA.text}","firstName":"Bob","lastName":"Bobbins","registrationTime":"$nowAsText","lastModified":"$nowAsText","verified":true,"organisation":"Bobbers","mfaDetails":[],"emailPreferences":{"interests":[],"topics":[]},"userId":"$userId"}"""

}
