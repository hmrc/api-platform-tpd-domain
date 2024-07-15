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

sealed trait MfaDetail {
  def id: MfaId
  def name: String
  def mfaType: MfaType
  def createdOn: Instant
  def verified: Boolean
}

object MfaDetail {
  import play.api.libs.json._
  import uk.gov.hmrc.play.json.Union

  implicit val authenticatorAppMfaDetailFormat: OFormat[AuthenticatorAppMfaDetail] = Json.format[AuthenticatorAppMfaDetail]
  implicit val smsMfaDetailFormat: OFormat[SmsMfaDetail]                           = Json.format[SmsMfaDetail]

  implicit val mfaDetailFormat: Format[MfaDetail] = Union.from[MfaDetail]("mfaType")
    .and[AuthenticatorAppMfaDetail](MfaType.AUTHENTICATOR_APP.toString)
    .and[SmsMfaDetail](MfaType.SMS.toString)
    .format
}

case class AuthenticatorAppMfaDetail(id: MfaId, name: String, createdOn: Instant, verified: Boolean = false)
    extends MfaDetail {
  override val mfaType: MfaType = MfaType.AUTHENTICATOR_APP
}

case class SmsMfaDetail(
    override val id: MfaId = MfaId.random,
    override val name: String,
    override val createdOn: Instant,
    mobileNumber: String,
    verified: Boolean = false
  ) extends MfaDetail {
  override val mfaType: MfaType = MfaType.SMS
}
