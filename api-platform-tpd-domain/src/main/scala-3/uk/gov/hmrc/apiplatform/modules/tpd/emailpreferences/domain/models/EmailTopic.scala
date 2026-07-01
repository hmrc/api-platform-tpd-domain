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

import play.api.libs.json.*
import uk.gov.hmrc.apiplatform.modules.common.domain.services.SimpleEnumJsonFormatting

enum EmailTopic(val displayOrder: Int, val displayName: String, val description: String):

  case BusinessAndPolicy extends EmailTopic(
        displayOrder = 1,
        displayName = "Business and policy",
        description = "Policy compliance, legislative changes and business guidance support"
      )

  case Technical extends EmailTopic(
        displayOrder = 2,
        displayName = "Technical",
        description = "Specifications, service guides, bug fixes and known errors"
      )

  case ReleaseSchedules extends EmailTopic(
        displayOrder = 3,
        displayName = "Release schedules",
        description = "Notifications about planned releases and outages"
      )

  case EventInvites extends EmailTopic(
        displayOrder = Int.MaxValue,
        displayName = "Event invites",
        description = "Get invites to knowledge share events and user research opportunities"
      )

object EmailTopic {

  def apply(text: String): Option[EmailTopic] = EmailTopic.values.find(_.toString.equalsIgnoreCase(text))

  def unsafeApply(text: String): EmailTopic = apply(text).getOrElse(throw new RuntimeException(s"$text is not a valid Email Topic"))

  given Format[EmailTopic] = SimpleEnumJsonFormatting.createEnumFormatFor[EmailTopic]("Email Topic", apply)

}
