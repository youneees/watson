package fr.mind7.watson.speechtotext

import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback
import fr.mind7.Configuration.BlueMix
import javax.sound.sampled._
import java.io.ByteArrayOutputStream

/**
  * Created by mind7 on 23/04/17.
  */
class AudioStreamingToTextService(model: String,
                                  maxAlternatives: Int = BlueMix.SpeechToText.Recognition.maxAlternatives,
                                  keywordsThreshold: Double = BlueMix.SpeechToText.Recognition.keywordsThreshold
                                 ) extends BaseRecognizeCallback {

  /*def recognizeUsingWebSocket() = {
    val format = new AudioFormat(16000.0f, 16, 1, true, true)

    val microphone: TargetDataLine = AudioSystem.getTargetDataLine(format)
    microphone.open(format)

    val out: ByteArrayOutputStream = new ByteArrayOutputStream
    var numBytesRead: Int = 0
    val CHUNK_SIZE: Int = 1024
    val data: Array[Byte] = new Array[Byte](microphone.getBufferSize / 5)

    var bytesRead = 0
    while(bytesRead < 100000) {
      numBytesRead = microphone.read(data, 0, CHUNK_SIZE)
      bytesRead += numBytesRead
      out.write(data, 0, numBytesRead)
    }

    microphone.close()
  }

  def recognize() = {

  }*/

}
