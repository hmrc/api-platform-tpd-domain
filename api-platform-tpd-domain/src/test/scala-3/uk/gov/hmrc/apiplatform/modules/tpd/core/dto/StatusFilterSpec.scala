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

package uk.gov.hmrc.apiplatform.modules.tpd.core.dto

import org.scalatest.prop.TableDrivenPropertyChecks

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.StatusFilter

class StatusFilterSpec extends BaseJsonFormattersSpec with TableDrivenPropertyChecks {

  "StatusFilter" should {
    val values =
      Table(
        ("Source", "json", "string"),
        (StatusFilter.Unverified, "UNVERIFIED", "Unverified"),
        (StatusFilter.Verified, "VERIFIED", "Verified"),
        (StatusFilter.All, "ALL", "All")
      )

    "convert to string correctly" in {
      forAll(values) { (s, _, t) =>
        s.toString() shouldBe t
      }
    }

    "convert lower case string to case object" in {
      forAll(values) { (s, t, _) =>
        StatusFilter.apply(t.toLowerCase) shouldBe Some(s)
        StatusFilter.unsafeApply(t) shouldBe s
      }
    }

    "convert string value to None when undefined or empty" in {
      StatusFilter.apply("rubbish") shouldBe None
      StatusFilter.apply("") shouldBe None
    }

    "throw when string value is invalid" in {
      intercept[RuntimeException] {
        StatusFilter.unsafeApply("rubbish")
      }.getMessage() should include("Status Filter")
    }

    "read from Json" in {
      forAll(values) { (s, j, _) =>
        testFromJson[StatusFilter](s""""$j"""")(s)
      }
    }

    "read with text error from Json" in {
      intercept[Exception] {
        testFromJson[StatusFilter](s""" "123" """)(StatusFilter.All)
      }.getMessage() should include("123 is not a valid Status Filter")
    }

    "read with error from Json" in {
      intercept[Exception] {
        testFromJson[StatusFilter](s"""123""")(StatusFilter.All)
      }.getMessage() should include("Cannot parse Status Filter from '123'")
    }

    "write to Json" in {
      forAll(values) { (s, j, _) =>
        Json.toJson[StatusFilter](s) shouldBe JsString(j)
      }
    }
  }

}
