package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import org.scalatest.OptionValues

import play.api.libs.json._

import uk.gov.hmrc.apiplatform.modules.common.utils._

class AccountSetupSpec extends BaseJsonFormattersSpec with OptionValues {

  "AccountSetup JsonFormatters" when {
    val example = AccountSetup(roles = List("role1"))

    "given an empty AccountSetup" should {
      "produce Json" in {
        testToJsonValues[AccountSetup](example)(
          ("roles" -> JsArray(Seq(JsString("role1")))),
          ("services" -> JsArray()),
          ("targets" -> JsArray()),
          ("incomplete" -> JsBoolean(true))
        )
      }

      "read json" in {
        testFromJson[AccountSetup]("""{"roles":["role1"],"services":[],"targets":[],"incomplete":true}""")(example)
      }
    }
  }
}
