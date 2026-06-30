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

package uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models._

class MfaDetailSpec extends BaseJsonFormattersSpec with FixedClock {

  private val authAppExample: MfaDetail = AuthenticatorAppMfaDetail(
    id = MfaId.random,
    name = "aName",
    createdOn = instant,
    verified = true
  )

  private val authAppJsonText = s"""{"id":"${authAppExample.id.toString()}","name":"aName","createdOn":"$nowAsText","verified":true,"mfaType":"AUTHENTICATOR_APP"}"""

  private val smsExample: MfaDetail = SmsMfaDetail(
    id = MfaId.random,
    name = "aName",
    createdOn = instant,
    mobileNumber = "07999123456",
    verified = true
  )

  private val smsAppJsonText = s"""{"id":"${smsExample.id.toString()}","name":"aName","createdOn":"$nowAsText","mobileNumber":"07999123456","verified":true,"mfaType":"SMS"}"""

  "MfaDetail JsonFormatters" when {

    "given an MfaDetail" should {
      "produce authenticator app Json" in {
        testToJsonValues[MfaDetail](authAppExample)(
          ("id"        -> JsString(authAppExample.id.toString())),
          ("name"      -> JsString("aName")),
          ("createdOn" -> JsString(nowAsText)),
          ("verified"  -> JsBoolean(true)),
          ("mfaType"   -> JsString(MfaType.AUTHENTICATOR_APP.toString()))
        )
      }

      "read authenticator app json" in {
        testFromJson[MfaDetail](authAppJsonText)(authAppExample)
      }

      "produce sms Json" in {
        testToJsonValues[MfaDetail](smsExample)(
          ("id"           -> JsString(smsExample.id.toString())),
          ("name"         -> JsString("aName")),
          ("createdOn"    -> JsString(nowAsText)),
          ("mobileNumber" -> JsString("07999123456")),
          ("verified"     -> JsBoolean(true)),
          ("mfaType"      -> JsString(MfaType.SMS.toString()))
        )
      }

      "read sms json" in {
        testFromJson[MfaDetail](smsAppJsonText)(smsExample)
      }
    }
  }
}
