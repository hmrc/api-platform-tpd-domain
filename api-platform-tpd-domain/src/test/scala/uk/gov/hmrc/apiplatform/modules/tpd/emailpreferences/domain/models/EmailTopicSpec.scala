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

package uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models

import scala.util.Random

import org.scalatest.prop.TableDrivenPropertyChecks

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.EmailTopic

class EmailTopicSpec extends BaseJsonFormattersSpec with TableDrivenPropertyChecks {

  "EmailTopic" should {
    val values =
      Table(
        ("Source", "json", "display name"),
        (EmailTopic.BUSINESS_AND_POLICY, "BUSINESS_AND_POLICY", "Business and policy"),
        (EmailTopic.EVENT_INVITES, "EVENT_INVITES", "Event invites"),
        (EmailTopic.RELEASE_SCHEDULES, "RELEASE_SCHEDULES", "Release schedules"),
        (EmailTopic.TECHNICAL, "TECHNICAL", "Technical")
      )

    "convert lower case string to case object" in {
      forAll(values) { (s, t, _) =>
        EmailTopic.apply(t.toLowerCase) shouldBe Some(s)
        EmailTopic.unsafeApply(t) shouldBe s
      }
    }

    "convert string value to None when undefined or empty" in {
      EmailTopic.apply("rubbish") shouldBe None
      EmailTopic.apply("") shouldBe None
    }

    "throw when string value is invalid" in {
      intercept[RuntimeException] {
        EmailTopic.unsafeApply("rubbish")
      }.getMessage() should include("Email Topic")
    }

    "read from Json" in {
      forAll(values) { (s, j, _) =>
        testFromJson[EmailTopic](s""""$j"""")(s)
      }
    }

    "read with text error from Json" in {
      intercept[Exception] {
        testFromJson[EmailTopic](s""" "123" """)(EmailTopic.TECHNICAL)
      }.getMessage() should include("123 is not a valid Email Topic")
    }

    "read with error from Json" in {
      intercept[Exception] {
        testFromJson[EmailTopic](s"""123""")(EmailTopic.TECHNICAL)
      }.getMessage() should include("Cannot parse Email Topic from '123'")
    }

    "write to Json" in {
      forAll(values) { (s, j, _) =>
        Json.toJson[EmailTopic](s) shouldBe JsString(j)
      }
    }

    "displayName is correct" in {
      forAll(values) { (s, _, d) =>
        s.displayName shouldBe d
      }
    }

    "description is present" in {
      forAll(values) { (s, _, _) =>
        s.description.isEmpty() shouldBe false
      }
    }

    "displayOrder orders correctly" in {
      import EmailTopic._
      Random.shuffle(EmailTopic.values.toList).sortBy(_.displayOrder) shouldBe List(BUSINESS_AND_POLICY, TECHNICAL, RELEASE_SCHEDULES, EVENT_INVITES)
    }
  }
}
