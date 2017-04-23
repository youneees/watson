package fr.mind7.watson

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel
import fr.mind7.Configuration.BlueMix

import scala.collection.JavaConverters._

/**
  * Created by mind7 on 22/04/17.
  */
object WatsonServicesManager {

  def speechToText: SpeechToText = {
    val speechToTextService: SpeechToText = new SpeechToText()
    speechToTextService.setUsernameAndPassword(BlueMix.Credentials.username,BlueMix.Credentials.password)

    speechToTextService
  }

  def speechToTextModel: Option[SpeechModel] = {
    // available models for wanted language
    val availableLanguageModels: List[SpeechModel] =
      speechToText
        .getModels.execute()
        .asScala.toList
        .filter(_.getLanguage equals BlueMix.SpeechToText.language)

    // return either the exact wanted model or another one from the same language if exists
    availableLanguageModels
      .find(_.getName equals BlueMix.SpeechToText.model)
      .orElse(availableLanguageModels.headOption)
  }

}
