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

package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import java.time.Instant

import play.api.libs.json._
import uk.gov.hmrc.play.json.Union

import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models._

case class UserResponse(
    email: String,
    firstName: String,
    lastName: String,
    registrationTime: Instant,
    lastModified: Instant,
    verified: Boolean,
    accountSetup: Option[AccountSetup] = None,
    organisation: Option[String] = None,
    mfaEnabled: Boolean = false,
    mfaDetails: List[MfaDetailResponse],
    nonce: Option[String] = None,
    emailPreferences: EmailPreferences = EmailPreferences.noPreferences,
    userId: UserId
  )

object UserResponse extends EnvReads with EnvWrites {

  implicit val dateTimeFormat: Format[Instant]                                             = Format(DefaultInstantReads, DefaultInstantWrites)
  implicit val authenticatorAppMfaDetailFormat: OFormat[AuthenticatorAppMfaDetailResponse] = Json.format[AuthenticatorAppMfaDetailResponse]
  implicit val smsMfaDetailFormat: OFormat[SmsMfaDetailResponse]                           = Json.format[SmsMfaDetailResponse]

  implicit val mfaDetailFormat: OFormat[MfaDetailResponse] = Union.from[MfaDetailResponse]("mfaType")
    .and[AuthenticatorAppMfaDetailResponse](MfaType.AUTHENTICATOR_APP.toString)
    .and[SmsMfaDetailResponse](MfaType.SMS.toString)
    .format

  implicit val format: OFormat[UserResponse] = Json.format[UserResponse]
}
