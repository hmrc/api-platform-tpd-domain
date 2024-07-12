package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import org.scalatest.OptionValues

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.EmailPreferences
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.TaxRegimeInterests
import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.MfaId
import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId
import uk.gov.hmrc.apiplatform.modules.tpd.mfa.domain.models.SmsMfaDetailResponse
import uk.gov.hmrc.apiplatform.modules.tpd.emailpreferences.domain.models.EmailTopic

class UserResponseSpec extends BaseJsonFormattersSpec with OptionValues with FixedClock {
  val mfaId = MfaId.random
  val userIdA = UserId.random
  
  "UserResponse JsonFormatters" when {
    val example = UserResponse(
      email = LaxEmailAddressData.emailA.text,
      firstName = "Bob",
      lastName = "Bobbins",
      registrationTime = instant,
      lastModified = instant,
      verified = true,
      accountSetup = None,
      organisation = Some("Bobbers"),
      mfaEnabled = true,
      mfaDetails = List(
        SmsMfaDetailResponse(
          id = mfaId,
          createdOn = instant,
          name = "xxx",
          mobileNumber = "07999123456",
          verified = true
        )
      ),
      nonce = None,
      emailPreferences = EmailPreferences(
        List(
          TaxRegimeInterests("CUSTOMS", Set("cds-api"))
        ),
        Set(EmailTopic.TECHNICAL)
      ),
      userId = userIdA
    )

    "given an empty UserResponse" should {
      "produce Json" in {
        testToJsonValues[UserResponse](example)(
          ("roles" -> JsArray(Seq(JsString("role1")))),
          ("services" -> JsArray()),
          ("targets" -> JsArray()),
          ("incomplete" -> JsBoolean(true))
        )
      }

      "read json" in {
        testFromJson[UserResponse]("""{"roles":["role1"],"services":[],"targets":[],"incomplete":true}""")(example)
      }
    }
  }
}
