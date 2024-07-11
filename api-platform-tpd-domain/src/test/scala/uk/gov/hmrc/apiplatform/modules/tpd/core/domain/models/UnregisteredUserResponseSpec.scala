package uk.gov.hmrc.apiplatform.modules.tpd.core.domain.models

import org.scalatest.OptionValues


import uk.gov.hmrc.apiplatform.modules.common.utils._
import uk.gov.hmrc.apiplatform.modules.common.domain.models.LaxEmailAddressData
import uk.gov.hmrc.apiplatform.modules.common.domain.models.UserId

class UnregisteredUserResponseSpec extends BaseJsonFormattersSpec with OptionValues with FixedClock {

  "UnregisteredUserResponse JsonFormatters" when {
    val userId = UserId.random
    val example = UnregisteredUserResponse(email = LaxEmailAddressData.emailA.text, creationTime = instant, userId = userId)

    "given an empty UnregisteredUserResponse" should {
      "produce Json" in {
        testToJson[UnregisteredUserResponse](example)(
          ("email" -> LaxEmailAddressData.emailA.text),
          ("creationTime" -> ""),
          ("userId" -> userId.toString())
        )
      }

      "read json" in {
        testFromJson[UnregisteredUserResponse](s"""{"email":"${LaxEmailAddressData.emailA.text}","creationTime":"","userId":"$userId"}""")(example)
      }
    }
  }
}
