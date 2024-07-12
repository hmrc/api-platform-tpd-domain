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

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.TaxRegimeInterests

class TaxRegimeInterestsSpec extends BaseJsonFormattersSpec {
  import TaxRegimeInterestsSpec._

  "TaxRegimeInterests JsonFormatters" when {

    "given an TaxRegimeInterests" should {
      "produce Json" in {
        testToJsonValues[TaxRegimeInterests](example)(
          jsonObject.fields.toSeq: _*
        )
      }

      "read json" in {
        testFromJson[TaxRegimeInterests](jsonText)(example)
      }
    }
  }
}

object TaxRegimeInterestsSpec {
  val example = TaxRegimeInterests(regime = "aRegime", services = Set("service1"))

  val jsonObject = JsObject(Seq(
    ("regime" -> JsString("aRegime")),
    ("services" -> JsArray(Seq(JsString("service1"))))
  ))
  val jsonText = s"""{"regime":"aRegime","services":["service1"]}"""
}