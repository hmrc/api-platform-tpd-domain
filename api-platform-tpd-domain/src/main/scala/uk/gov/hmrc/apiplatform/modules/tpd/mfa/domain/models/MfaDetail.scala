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

package uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models

import java.time.Instant
import java.util.UUID

import play.api.libs.json.{Format, Json}

case class MfaId(value: UUID) extends AnyVal

object MfaId {
  implicit val format: Format[MfaId] = Json.valueFormat[MfaId]

  def random: MfaId = MfaId(UUID.randomUUID())
}

sealed trait MfaType

object MfaType {
  val values: Set[MfaType] = Set(AUTHENTICATOR_APP, SMS)

  case object AUTHENTICATOR_APP extends MfaType
  case object SMS               extends MfaType
}

sealed trait MfaDetailResponse {
  def id: MfaId
  def name: String
  def mfaType: MfaType
  def createdOn: Instant
  def verified: Boolean
}

case class AuthenticatorAppMfaDetailResponse(id: MfaId, name: String, createdOn: Instant, verified: Boolean = false)
    extends MfaDetailResponse {
  override val mfaType: MfaType = MfaType.AUTHENTICATOR_APP
}

case class SmsMfaDetailResponse(
    override val id: MfaId = MfaId.random,
    override val name: String,
    override val createdOn: Instant,
    mobileNumber: String,
    verified: Boolean = false
  ) extends MfaDetailResponse {
  override val mfaType: MfaType = MfaType.SMS
}
