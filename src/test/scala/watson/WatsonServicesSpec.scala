package watson

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel
import fr.mind7.Configuration._
import fr.mind7.watson.WatsonServicesManager
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

/**
  * Created by mind7 on 22/04/17.
  */
class WatsonServicesSpec extends FlatSpec with Matchers with GivenWhenThen{

  "WatsonServicesManager" should "initialize SpeechToTextService with default SpeechModel" in {
    val requiredConfPaths = Seq(
      "bluemix.credentials.username",
      "bluemix.credentials.password",
      "bluemix.speechtotext.language",
      "bluemix.speechtotext.model"
    )

    Given("required configuration")
    requiredConfPaths.forall(config.hasPath) should be(true)

    When("Speech model is initialized")
    val maybeSpeechModel = WatsonServicesManager.speechToTextModel

    Then("it is initialized with the correct language")
    maybeSpeechModel should matchPattern {
      case Some(speechModel: SpeechModel) if speechModel.getLanguage equals BlueMix.SpeechToText.language=>
    }
    if(! maybeSpeechModel.get.getName.equals(BlueMix.SpeechToText.model))
      println(s"WARN : unavailable model [${BlueMix.SpeechToText.model}], continuing with alternative model [${maybeSpeechModel.get.getName}]")

  }

}
