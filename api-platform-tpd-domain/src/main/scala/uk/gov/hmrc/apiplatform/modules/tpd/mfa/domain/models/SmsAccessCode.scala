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

import play.api.libs.json.{Format, Json}

import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId

case class SmsAccessCode(userId: UserId, mfaId: MfaId, accessCode: String, createdTime: Instant)

object SmsAccessCode {
  implicit val format: Format[SmsAccessCode] = Json.format[SmsAccessCode]
}
