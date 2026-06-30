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

import org.scalatest.prop.TableDrivenPropertyChecks

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.{EmailPreferences, EmailTopic}

class EmailPreferencesSpec extends BaseJsonFormattersSpec with TableDrivenPropertyChecks {
  import EmailPreferencesSpec._

  private val fullExample: EmailPreferences = EmailPreferences(interests = List(TaxRegimeInterestsSpec.example), topics = Set(EmailTopic.BUSINESS_AND_POLICY))

  private val fullJsonText = s"""{"interests":[${TaxRegimeInterestsSpec.jsonText}],"topics":["BUSINESS_AND_POLICY"]}"""

  "EmailPreferences JsonFormatters" when {

    "given an EmailPreferences" should {
      "produce Json" in {
        testToJsonValues[EmailPreferences](example)(jsonObject.fields.toSeq: _*)
      }

      "produce full Json" in {
        testToJsonValues[EmailPreferences](fullExample)(
          ("interests" -> JsArray(Seq(TaxRegimeInterestsSpec.jsonObject))),
          ("topics"    -> JsArray(Seq(JsString("BUSINESS_AND_POLICY"))))
        )
      }

      "read json" in {
        testFromJson[EmailPreferences](jsonText)(example)
      }

      "read full json" in {
        testFromJson[EmailPreferences](fullJsonText)(fullExample)
      }
    }
  }
}

object EmailPreferencesSpec {

  val example: EmailPreferences = EmailPreferences(interests = List.empty, topics = Set.empty)

  val jsonObject = JsObject(Seq(
    ("interests" -> JsArray.empty),
    ("topics"    -> JsArray.empty)
  ))

  val jsonText = s"""{"interests":[],"topics":[]}"""

}
