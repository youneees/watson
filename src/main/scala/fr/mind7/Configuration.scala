package fr.mind7

import com.typesafe.config.ConfigFactory

/**
  * Created by mind7 on 22/04/17.
  */
object Configuration {

  lazy val config = ConfigFactory.defaultApplication().resolve()

  object BlueMix {
    object Credentials {
      lazy val username = config.getString("bluemix.credentials.username")
      lazy val password = config.getString("bluemix.credentials.password")
    }

    object SpeechToText {
      lazy val language = config.getString("bluemix.speechtotext.language")
      lazy val model = config.getString("bluemix.speechtotext.model")

      object Recognition {
        lazy val maxAlternatives = config.getInt("bluemix.speechtotext.recognition.max-alternatives")
        lazy val keywordsThreshold = config.getDouble("bluemix.speechtotext.recognition.keywords-threshold")
      }
    }
  }

}
