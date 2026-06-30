/*
 * Copyright 2023 HM Revenue & Customs
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

import uk.gov.hmrc.apiplatform.modules.common.domain.services.SealedTraitJsonFormatting

sealed trait StatusFilter

case object StatusFilter {
  case object Unverified extends StatusFilter
  case object Verified   extends StatusFilter
  case object All        extends StatusFilter

  val values = Set[StatusFilter](Unverified, Verified, All)

  def apply(value: String): Option[StatusFilter] =
    value.toUpperCase match {
      case "UNVERIFIED" => Some(StatusFilter.Unverified)
      case "VERIFIED"   => Some(StatusFilter.Verified)
      case "ALL"        => Some(StatusFilter.All)
      case _            => None
    }

  def unsafeApply(value: String): StatusFilter =
    apply(value).getOrElse(throw new RuntimeException("Invalid Status Filter: " + value))

  import play.api.libs.json.Format

  implicit val format: Format[StatusFilter] = SealedTraitJsonFormatting.createFormatFor[StatusFilter]("Status Filter", apply(_), t => t.toString.toUpperCase)
}
