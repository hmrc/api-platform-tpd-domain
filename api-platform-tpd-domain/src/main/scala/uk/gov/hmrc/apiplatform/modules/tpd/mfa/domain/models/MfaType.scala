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

import uk.gov.hmrc.apiplatform.modules.common.domain.services.SealedTraitJsonFormatting

sealed trait MfaType

object MfaType {
  import play.api.libs.json.Format

  val values: Set[MfaType] = Set(AUTHENTICATOR_APP, SMS)

  case object AUTHENTICATOR_APP extends MfaType
  case object SMS               extends MfaType

  def apply(text: String): Option[MfaType] = MfaType.values.find(_.toString() == text.toUpperCase)

  def unsafeApply(text: String): MfaType = apply(text).getOrElse(throw new RuntimeException(s"$text is not a valid Mfa Type"))

  implicit val format: Format[MfaType] = SealedTraitJsonFormatting.createFormatFor[MfaType]("Mfa Type", apply)

}
