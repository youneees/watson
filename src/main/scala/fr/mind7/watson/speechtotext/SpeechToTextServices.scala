package fr.mind7.watson.speechtotext


/**
  * Created by mind7 on 22/04/17.
  */
object SpeechToTextServices {

  object FileInput {
    lazy val EN_US_Service = new AudioFileToTextService(model = "en-US_BroadbandModel")
    lazy val EN_UK_Service = new AudioFileToTextService(model = "en-UK_BroadbandModel")
    lazy val FR_FR_Service = new AudioFileToTextService(model = "fr-FR_BroadbandModel")
  }

}
