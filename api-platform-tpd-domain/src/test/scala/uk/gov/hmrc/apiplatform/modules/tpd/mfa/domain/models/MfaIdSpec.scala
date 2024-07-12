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
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaId

class MfaIdSpec extends BaseJsonFormattersSpec {

  "MfaId JsonFormatters" when {
    val example = MfaId.random

    "toString" in {
      example.toString() shouldBe example.value.toString()
    }

    "generate a random value" in {
      MfaId.random should not be MfaId.random
    }

    "apply raw text" in {
      val in = MfaId.random

      MfaId.apply(in.value.toString()) shouldBe Some(in)
    }

    "apply raw text fails when not valid" in {
      MfaId.apply("not-a-uuid") shouldBe None
    }

    "unsafeApply text" in {
      val in = MfaId.random

      MfaId.unsafeApply(in.value.toString()) shouldBe in
    }

    "unsafeApply raw text throws when not valid" in {
      intercept[RuntimeException] {
        MfaId.unsafeApply("not-a-uuid")
      }
    }

    "convert to json" in {
      Json.toJson(example) shouldBe JsString(example.value.toString())
    }

    "read from json" in {
      testFromJson[MfaId](s""""${example.value.toString}"""")(example)
    }
  }
}
