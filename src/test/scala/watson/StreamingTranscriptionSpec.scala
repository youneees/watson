package watson

import java.nio.file.Files
import javax.sound.sampled._

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions
import fr.mind7.Configuration.BlueMix
import org.scalatest.FlatSpec
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback

/**
  * Created by mind7 on 23/04/17.
  */
class StreamingTranscriptionSpec extends FlatSpec{

  "A" should "B" in {

    val speechToTextService: SpeechToText = new SpeechToText()
    speechToTextService.setUsernameAndPassword(BlueMix.Credentials.username,BlueMix.Credentials.password)

    val options = new RecognizeOptions.Builder()
      .model("en-US_BroadbandModel")
      .contentType("audio/wav")
      .continuous(true)
      .interimResults(true)
      .maxAlternatives(10)
      .keywords("prison","escape","war","brother")
      .keywordsThreshold(0.5)
      .build

    val callback = new BaseRecognizeCallback() {
      override def onTranscription(speechResults: SpeechResults): Unit = {
        println("transcipted =")
        println(speechResults.toString)
      }

      override def onDisconnected(): Unit = {
        System.exit(0)
      }

      override def onListening(): Unit = {
        println("=====> is listening")
      }
    }

    val format = new AudioFormat(8000.0f, 16, 1, true, true)

    val microphone: TargetDataLine = AudioSystem.getTargetDataLine(format)
    microphone.open(format)

    /*
    speechToTextService.recognizeUsingWebSocket(
      new AudioInputStream(microphone),
      options,
      callback)*/
  }
}
