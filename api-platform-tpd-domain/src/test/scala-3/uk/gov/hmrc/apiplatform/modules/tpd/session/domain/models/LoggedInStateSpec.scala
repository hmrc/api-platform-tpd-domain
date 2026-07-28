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

import org.scalatest.prop.TableDrivenPropertyChecks

import play.api.libs.json.*
import uk.gov.hmrc.apiplatform.modules.common.utils.*

import uk.gov.hmrc.apiplatform.modules.tpd.session.domain.models.LoggedInState

class LoggedInStateSpec extends BaseJsonFormattersSpec with TableDrivenPropertyChecks {

  "LoggedInState" should {
    val values =
      Table(
        ("Source", "json", "string"),
        (LoggedInState.LoggedIn, "LoggedIn", "Unverified"),
        (LoggedInState.PartLoggedInEnablingMFA, "PartLoggedInEnablingMfa", "Verified")
      )

    val jsonValues =
      Table(
        ("Source", "json"),
        (LoggedInState.LoggedIn, "LOGGED_IN"),
        (LoggedInState.PartLoggedInEnablingMFA, "PART_LOGGED_IN_ENABLING_MFA")
      )

    "be logged in for LoggedIn" in {
      LoggedInState.LoggedIn.isLoggedIn shouldBe true
      LoggedInState.PartLoggedInEnablingMFA.isLoggedIn shouldBe false
    }

    "be part logged in enabling MFA for PartLoggedInEnablingMFA" in {
      LoggedInState.PartLoggedInEnablingMFA.isPartLoggedInEnablingMFA shouldBe true
      LoggedInState.LoggedIn.isPartLoggedInEnablingMFA shouldBe false
    }

    "convert lower case string to case object" in {
      forAll(values) { (s, t, _) =>
        LoggedInState.apply(t.toLowerCase) shouldBe Some(s)
        LoggedInState.unsafeApply(t) shouldBe s
      }
    }

    "convert string value to None when undefined or empty" in {
      LoggedInState.apply("rubbish") shouldBe None
      LoggedInState.apply("") shouldBe None
    }

    "throw when string value is invalid" in {
      intercept[RuntimeException] {
        LoggedInState.unsafeApply("rubbish")
      }.getMessage() should include("Logged In State")
    }

    "read from Json" in {
      forAll(jsonValues) { (s, j) =>
        testFromJson[LoggedInState](s""""$j"""")(s)
      }
    }

    "read with text error from Json" in {
      intercept[Exception] {
        testFromJson[LoggedInState](s""" "123" """)(LoggedInState.LoggedIn)
      }.getMessage() should include("123 is not a valid Logged In State")
    }

    "read with error from Json" in {
      intercept[Exception] {
        testFromJson[LoggedInState](s"""123""")(LoggedInState.LoggedIn)
      }.getMessage() should include("Cannot parse Logged In State from '123'")
    }

    "write to Json" in {
      forAll(jsonValues) { (s, j) =>
        Json.toJson[LoggedInState](s) shouldBe JsString(j)
      }
    }
  }
}
