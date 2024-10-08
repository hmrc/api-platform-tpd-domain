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

package uk.gov.hmrc.apiplatform.modules.tpd.core.dto

import play.api.libs.json.{Json, OFormat}

import uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models.AccountSetup

case class AccountSetupRequest(
    roles: Option[List[String]] = None,
    rolesOther: Option[String] = None,
    services: Option[List[String]] = None,
    servicesOther: Option[String] = None,
    targets: Option[List[String]] = None,
    targetsOther: Option[String] = None,
    incomplete: Option[Boolean] = None
  ) {

  def toAccountSetup: AccountSetup = {
    AccountSetup(
      roles.getOrElse(List.empty),
      rolesOther,
      services.getOrElse(List.empty),
      servicesOther,
      targets.getOrElse(List.empty),
      targetsOther,
      incomplete.getOrElse(false)
    )
  }

}

object AccountSetupRequest {
  implicit val format: OFormat[AccountSetupRequest] = Json.format[AccountSetupRequest]
}
