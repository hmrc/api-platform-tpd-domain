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

package uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.dto

import play.api.libs.json.{Json, OFormat}

case class SendEmailRequest(
    to: List[String],
    templateId: String,
    parameters: Map[String, String],
    force: Boolean = false,
    auditData: Map[String, String] = Map.empty,
    eventUrl: Option[String] = None
  )

object SendEmailRequest {
  implicit val sendEmailRequestFmt: OFormat[SendEmailRequest] = Json.format[SendEmailRequest]

  def createVerification(verificationUrl: String, emailAddress: String, verificationCode: String, devHubTitle: String) = {
    def createVerificationUrl(verificationUrl: String, code: String) = verificationUrl + code
    SendEmailRequest(
      to = List(emailAddress),
      templateId = "apiDeveloperEmailVerification",
      parameters = Map("verificationLink" -> createVerificationUrl(verificationUrl, verificationCode), "developerHubTitle" -> devHubTitle)
    )
  }

  def createPasswordReset(resetUrl: String, emailAddress: String, devHubTitle: String) = {
    SendEmailRequest(
      to = List(emailAddress),
      templateId = "apiDeveloperPasswordReset",
      parameters = Map("resetPasswordLink" -> resetUrl, "developerHubTitle" -> devHubTitle)
    )
  }

  def createPasswordChanged(emailAddress: String, devHubTitle: String) = {
    SendEmailRequest(
      to = List(emailAddress),
      templateId = "apiDeveloperChangedPasswordConfirmation",
      parameters = Map("developerHubTitle" -> devHubTitle)
    )
  }

  def createDeveloperDeleted(emailAddress: String, devHubTitle: String) = {
    SendEmailRequest(
      to = List(emailAddress),
      templateId = "apiDeveloperDeletedConfirmation",
      parameters = Map("developerHubTitle" -> devHubTitle)
    )
  }
}
