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

package uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.domain.services.SealedTraitJsonFormatting

sealed trait EmailTopic

object EmailTopic {

  val values = Set(BUSINESS_AND_POLICY, TECHNICAL, RELEASE_SCHEDULES, EVENT_INVITES)

  case object BUSINESS_AND_POLICY extends EmailTopic

  case object TECHNICAL extends EmailTopic

  case object RELEASE_SCHEDULES extends EmailTopic

  case object EVENT_INVITES extends EmailTopic

  def apply(text: String): Option[EmailTopic] = EmailTopic.values.find(_.toString() == text.toUpperCase)

  def unsafeApply(text: String): EmailTopic = apply(text).getOrElse(throw new RuntimeException(s"$text is not a valid Email Topic"))

  implicit val format: Format[EmailTopic] = SealedTraitJsonFormatting.createFormatFor[EmailTopic]("Email Topic", apply)

}
