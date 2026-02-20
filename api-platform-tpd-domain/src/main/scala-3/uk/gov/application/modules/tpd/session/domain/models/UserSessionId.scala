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

package uk.gov.hmrc.apiplatform.modules.tpd.session.domain.models

import java.{util => ju}
import play.api.libs.json.Format

import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.SessionId

opaque type UserSessionId <: SessionId = SessionId

object UserSessionId {
  extension (u: UserSessionId) {
    def value: ju.UUID = u
  }
  
  import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.SessionId.{reads, writes}
  given format: Format[UserSessionId] = Format(reads, writes)

  def apply(s: SessionId): UserSessionId = s
  def apply(raw: String): Option[UserSessionId] = SessionId(raw)
  def unsafeApply(raw: String): UserSessionId = apply(raw).getOrElse(throw new RuntimeException(s"$raw is not a valid UserSessionId"))

// $COVERAGE-OFF$
  def random: UserSessionId = SessionId(ju.UUID.randomUUID())
// $COVERAGE-ON$
}
