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

package uk.gov.hmrc.apiplatform.modules.tpd.test.builders

import uk.gov.hmrc.apiplatform.modules.common.domain.models.{LaxEmailAddress, UserId}
import uk.gov.hmrc.apiplatform.modules.tpd.session.domain.models.{LoggedInState, UserSession, UserSessionId}
import uk.gov.hmrc.apiplatform.modules.tpd.test.utils.LocalUserIdTracker

trait UserSessionBuilder extends UserBuilder with LocalUserIdTracker {

  def buildSession(sessionId: UserSessionId, userId: UserId = UserId.random, firstName: String, lastName: String, userEmail: LaxEmailAddress): UserSession = {
    UserSession(
      sessionId,
      LoggedInState.LOGGED_IN,
      buildUser(userEmail, firstName, lastName).copy(userId = userId, verified = true)
    )
  }
}
