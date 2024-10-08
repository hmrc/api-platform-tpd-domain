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

package uk.gov.hmrc.apiplatform.modules.tpd.session.domain.models

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.UserSpec

class UserSessionSpec extends BaseJsonFormattersSpec with FixedClock {
  import UserSessionSpec._

  "UserSession JsonFormatters" when {

    "given a UserSession" should {
      "produce Json" in {
        testToJsonValues[UserSession](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[UserSession](UserSessionSpec.jsonText)(example)
      }
    }
  }
}

object UserSessionSpec extends FixedClock {

  val example = UserSession(
    sessionId = UserSessionId.random,
    loggedInState = LoggedInState.LOGGED_IN,
    developer = UserSpec.example
  )

  val jsonObject = JsObject(Seq(
    ("sessionId"     -> JsString(example.sessionId.toString())),
    ("loggedInState" -> JsString("LOGGED_IN")),
    ("developer"     -> UserSpec.jsonObject)
  ))

  val jsonText =
    s"""{"sessionId":"${example.sessionId.toString}","loggedInState":"LOGGED_IN","developer":${UserSpec.jsonText}}"""

}
