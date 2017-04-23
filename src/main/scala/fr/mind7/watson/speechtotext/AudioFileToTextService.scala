package fr.mind7.watson.speechtotext

import java.io.File
import java.nio.file.Files

import com.ibm.watson.developer_cloud.http.ServiceCall
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.{RecognizeOptions, SpeechResults}
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback
import fr.mind7.Configuration.BlueMix
import fr.mind7.watson.WatsonServicesManager

/**
  * Created by mind7 on 22/04/17.
  */
class AudioFileToTextService(model: String,
                             maxAlternatives: Int = BlueMix.SpeechToText.Recognition.maxAlternatives,
                             keywordsThreshold: Double = BlueMix.SpeechToText.Recognition.keywordsThreshold
                            ) extends BaseRecognizeCallback {

  def recognize(audioFile: File, keywords: String*): SpeechResults =
    serviceCall(audioFile, keywords:_*).execute()

  def recognize(audioFilePath: String, keywords: String*): SpeechResults =
    recognize(new File(audioFilePath),keywords:_*)

  private def serviceCall(audioFile: File, keywords: String*): ServiceCall[SpeechResults] = {
    WatsonServicesManager
      .speechToText
      .recognize(
        audioFile,
        buildRecognizeOptions(audioFile,keywords:_*)
      )
  }

  // FIXME : internalize build and externalize both keywordsThreshold and maxAlternatives
  // BEWARE : settings dependency => cannot define keywordsThreshold if keywords list is empty
  private def buildRecognizeOptions(audioFile: File, keywords: String*): RecognizeOptions =
    if(keywords.isEmpty)
      new RecognizeOptions.Builder()
        .model(model)
        .contentType(Files.probeContentType(audioFile.toPath))
        .continuous(true)
        .interimResults(true)
        .maxAlternatives(maxAlternatives)
        .build
    else
      new RecognizeOptions.Builder()
        .model(model)
        .contentType(Files.probeContentType(audioFile.toPath))
        .continuous(true)
        .interimResults(true)
        .maxAlternatives(maxAlternatives)
        .keywords(keywords:_*)
        .keywordsThreshold(keywordsThreshold)
        .build
}
