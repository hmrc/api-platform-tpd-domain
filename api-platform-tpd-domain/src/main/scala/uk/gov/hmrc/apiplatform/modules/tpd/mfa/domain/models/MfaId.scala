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

import java.{util => ju}
import scala.util.control.Exception._

case class MfaId(value: ju.UUID) extends AnyVal {
  override def toString(): String = value.toString()
}

object MfaId {
  import play.api.libs.json.{Format, Json}

  implicit val format: Format[MfaId] = Json.valueFormat[MfaId]

  def apply(raw: String): Option[MfaId] = allCatch.opt(MfaId(ju.UUID.fromString(raw)))

  def unsafeApply(raw: String): MfaId = apply(raw).getOrElse(throw new RuntimeException(s"$raw is not a valid MfaId"))

// $COVERAGE-OFF$
  def random: MfaId = MfaId(ju.UUID.randomUUID())
// $COVERAGE-ON$
}