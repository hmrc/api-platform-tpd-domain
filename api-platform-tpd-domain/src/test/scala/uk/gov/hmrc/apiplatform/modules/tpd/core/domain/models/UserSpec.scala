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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddressFixtures, UserId}
import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.{EmailPreferences, EmailPreferencesSpec}
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.{MfaId, SmsMfaDetail}

class UserSpec extends BaseJsonFormattersSpec with FixedClock with LaxEmailAddressFixtures {
  import UserSpec._

  "User" should {
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

    "show displayName" in {
      example.displayedName shouldBe "Bob Bobbins"
    }

    "not verified when no mfa is verified" in {
      example.hasVerifiedMfa shouldBe false
    }

    "verified when mfa is verified" in {
      fullExample.hasVerifiedMfa shouldBe true
    }

  }

  val fullExample = example.copy(
    mfaDetails = List(
      SmsMfaDetail(
        id = mfaId,
        createdOn = Instants.aYearAgo,
        name = "xxx",
        mobileNumber = "07999123456",
        verified = true
      )
    ),
    emailPreferences = EmailPreferencesSpec.example,
    failedLogins = 2,
    lastLogin = Some(Instants.aMonthAgo)
  )

  val fullJsonText =
    s"""{"email":"$emailOne","firstName":"Bob","lastName":"Bobbins","registrationTime":"${Texts.aYearAgo}","lastModified":"$nowAsText","verified":true,"mfaDetails":[{"id":"$mfaId","name":"xxx","createdOn":"${Texts.aYearAgo}","mobileNumber":"07999123456","verified":true,"mfaType":"SMS"}],"emailPreferences":${EmailPreferencesSpec.jsonText},"userId":"$userId","failedLogins":2,"lastLogin":"${Texts.aMonthAgo}"}"""

}

object UserSpec extends FixedClock with LaxEmailAddressFixtures {
  val mfaId  = MfaId.random
  val userId = UserId.random

  val example = User(
    email = emailOne,
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

  val jsonObject = JsObject(Seq(
    ("userId"           -> JsString(userId.toString())),
    ("email"            -> JsString(emailOne.text)),
    ("firstName"        -> JsString("Bob")),
    ("lastName"         -> JsString("Bobbins")),
    ("registrationTime" -> JsString(Texts.aYearAgo)),
    ("lastModified"     -> JsString(nowAsText)),
    ("verified"         -> JsBoolean(true)),
    ("mfaDetails"       -> JsArray.empty),
    ("emailPreferences" -> EmailPreferencesSpec.jsonObject),
    ("failedLogins"     -> JsNumber(0))
  ))

  val jsonText =
    s"""{"email":"$emailOne","firstName":"Bob","lastName":"Bobbins","registrationTime":"${Texts.aYearAgo}","lastModified":"$nowAsText","verified":true,"mfaDetails":[],"emailPreferences":{"interests":[],"topics":[]},"userId":"$userId"}"""

}
