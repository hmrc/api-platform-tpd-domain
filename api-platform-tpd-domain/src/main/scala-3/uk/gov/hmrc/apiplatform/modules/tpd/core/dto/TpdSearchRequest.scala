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

import java.time.LocalDate

import play.api.libs.json.{Json, OFormat}

import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.StatusFilter
import uk.gov.hmrc.apiplatform.modules.tpd.core.dto.StatusFilter.All

case class TpdSearchRequest(
    emailFilter: Option[String] = None,
    textFilter: Option[String] = None,
    status: StatusFilter = All,
    limit: Option[Int] = None,
    createdBefore: Option[LocalDate] = None,
    createdAfter: Option[LocalDate] = None,
    pagination: Option[Pagination] = None
  )

object TpdSearchRequest {
  given OFormat[TpdSearchRequest] = Json.format[TpdSearchRequest]
}
case class Pagination(pageSize: Int, pageNbr: Int)

case object Pagination {
  given OFormat[Pagination] = Json.format[Pagination]

}
