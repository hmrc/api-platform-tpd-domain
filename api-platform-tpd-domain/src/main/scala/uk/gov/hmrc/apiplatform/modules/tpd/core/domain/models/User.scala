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

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddress, UserId}
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models._
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models._

case class User(
    email: LaxEmailAddress,
    firstName: String,
    lastName: String,
    registrationTime: Instant,
    lastModified: Instant,
    verified: Boolean,
    accountSetup: Option[AccountSetup] = None,
    mfaDetails: List[MfaDetail],
    nonce: Option[String] = None,
    emailPreferences: EmailPreferences = EmailPreferences.noPreferences,
    userId: UserId,
    failedLogins: Int = 0,
    lastLogin: Option[Instant] = None
  ) {
  lazy val displayedName: String   = s"$firstName $lastName"
  lazy val hasVerifiedMfa: Boolean = mfaDetails.exists(_.verified)
}

object User extends EnvReads with EnvWrites {

  implicit val dateTimeFormat: Format[Instant] = Format(DefaultInstantReads, DefaultInstantWrites)

  implicit val format: OFormat[User] = Json.using[Json.WithDefaultValues].format[User]
}
