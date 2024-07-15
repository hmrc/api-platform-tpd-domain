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
import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.UserResponseSpec

class UserSessionResponseSpec extends BaseJsonFormattersSpec with FixedClock {
  import UserSessionResponseSpec._

  "UserSessionResponse JsonFormatters" when {

    "given a UserSessionResponse" should {
      "produce Json" in {
        testToJsonValues[UserSessionResponse](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[UserSessionResponse](UserSessionResponseSpec.jsonText)(example)
      }
    }
  }
}

object UserSessionResponseSpec extends FixedClock {
  val example = UserSessionResponse(
    sessionId = UserSessionId.random,
    loggedInState = LoggedInState.LOGGED_IN,
    developer = UserResponseSpec.example
  )

  val jsonObject = JsObject(Seq(
    ("sessionId"           -> JsString(example.sessionId.toString())),
    ("loggedInState"       -> JsString("LOGGED_IN")),
    ("developer"           -> UserResponseSpec.jsonObject),
  ))

  val jsonText =
    s"""{"sessionId":"${example.sessionId.toString}","loggedInState":"LOGGED_IN","developer":${UserResponseSpec.jsonText}}"""

}
