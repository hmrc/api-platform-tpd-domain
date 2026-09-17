/*
 * Copyright 2026 HM Revenue & Customs
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

import play.api.libs.json.*
import uk.gov.hmrc.apiplatform.modules.common.utils.*

class TpdSearchRequestSpec extends BaseJsonFormattersSpec with FixedClock {

  "TpdSearchRequest JsonFormatters" when {
    val example = TpdSearchRequest(
      emailFilter = Some("a@b.com"),
      status = StatusFilter.All,
      textFilter = Some("*Dave*"),
      limit = Some(10),
      createdBefore = Some(now.toLocalDate),
      createdAfter = Some(now.toLocalDate),
      pagination = Some(Pagination(10, 1))
    )

    val defaultRequest = TpdSearchRequest()

    "given an SearchParameters" should {
      "produce full Json" in {
        testToJsonValues[TpdSearchRequest](example)(
          ("emailFilter"   -> JsString("a@b.com")),
          ("textFilter"    -> JsString("*Dave*")),
          ("status"        -> JsString("ALL")),
          ("limit"         -> JsNumber(10)),
          ("createdBefore" -> JsString("2020-01-02")),
          ("createdAfter"  -> JsString("2020-01-02")),
          ("pagination"    -> JsObject(Seq(("pageSize", JsNumber(10)), ("pageNbr", JsNumber(1)))))
        )
      }
      "produce default Json" in {
        testToJson[TpdSearchRequest](defaultRequest)(
          ("status" -> "ALL")
        )
      }

      "read full json" in {
        testFromJson[TpdSearchRequest]("""{"emailFilter":"a@b.com","textFilter":"*Dave*","status":"ALL","limit":10,"createdBefore":"2020-01-02","createdAfter":"2020-01-02", "pagination":{"pageSize":10,"pageNbr":1}}""")(
          example
        )
      }
      "read default json" in {
        testFromJson[TpdSearchRequest]("""{}""")(
          defaultRequest
        )
      }
    }
  }
}
