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

import org.scalatest.prop.TableDrivenPropertyChecks

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaType

class MfaTypeSpec extends BaseJsonFormattersSpec with TableDrivenPropertyChecks {

  "MfaType" should {
    val values =
      Table(
        ("Source", "json"),
        (MfaType.AUTHENTICATOR_APP, "AUTHENTICATOR_APP"),
        (MfaType.SMS, "SMS")
      )

    "convert lower case string to case object" in {
      forAll(values) { (s, t) =>
        MfaType.apply(t.toLowerCase) shouldBe Some(s)
        MfaType.unsafeApply(t) shouldBe s
      }
    }

    "convert string value to None when undefined or empty" in {
      MfaType.apply("rubbish") shouldBe None
      MfaType.apply("") shouldBe None
    }

    "throw when string value is invalid" in {
      intercept[RuntimeException] {
        MfaType.unsafeApply("rubbish")
      }.getMessage() should include("Mfa Type")
    }

    "read from Json" in {
      forAll(values) { (s, j) =>
        testFromJson[MfaType](s""""$j"""")(s)
      }
    }

    "read with text error from Json" in {
      intercept[Exception] {
        testFromJson[MfaType](s""" "123" """)(MfaType.SMS)
      }.getMessage() should include("123 is not a valid Mfa Type")
    }

    "read with error from Json" in {
      intercept[Exception] {
        testFromJson[MfaType](s"""123""")(MfaType.SMS)
      }.getMessage() should include("Cannot parse Mfa Type from '123'")
    }

    "write to Json" in {
      forAll(values) { (s, j) =>
        Json.toJson[MfaType](s) shouldBe JsString(j)
      }
    }
  }
}
