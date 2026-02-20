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

class UserSessionIdSpec extends BaseJsonFormattersSpec {

  "UserSessionId JsonFormatters" when {
    val example = UserSessionId.random

    "toString" in {
      example.toString() shouldBe example.value.toString()
    }

    "generate a random value" in {
      UserSessionId.random should not be UserSessionId.random
    }

    "apply raw text" in {
      val in = UserSessionId.random

      UserSessionId.apply(in.value.toString()) shouldBe Some(in)
    }

    "apply raw text fails when not valid" in {
      UserSessionId.apply("not-a-uuid") shouldBe None
    }

    "unsafeApply text" in {
      val in = UserSessionId.random

      UserSessionId.unsafeApply(in.value.toString()) shouldBe in
    }

    "unsafeApply raw text throws when not valid" in {
      intercept[RuntimeException] {
        UserSessionId.unsafeApply("not-a-uuid")
      }
    }

    "convert to json" in {
      Json.toJson(example) shouldBe JsString(example.value.toString())
    }

    "read from json" in {
      testFromJson[UserSessionId](s""""${example.value.toString}"""")(example)
    }
  }
}
