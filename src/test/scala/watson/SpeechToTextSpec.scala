package watson

import java.io.File
import java.nio.file.Files

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults
import fr.mind7.watson.speechtotext.SpeechToTextServices
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}
import scala.collection.JavaConverters._

/**
  * Created by mind7 on 22/04/17.
  */
class SpeechToTextSpec extends FlatSpec with Matchers with GivenWhenThen {

  lazy val flacFile: File = new File(getClass.getResource("/audio-file.flac").getPath)

  "SpeechToTextService" should "auto acquired mime-type from file" in {
    Given("A file with a known mime-type")
    val audioFile : File = flacFile

    Then("the same mime-type is retreived using java.nio.file.Files.probeContentType")
    Files.probeContentType(audioFile.toPath).toLowerCase should equal("audio/flac")
  }

  it should "recognize speech and evaluate keywords" in {
    Given("A path to audio file")
    val audioFilePath = flacFile.getAbsolutePath

    When(s"$it recognition is called")
    val keywords: Seq[String] = Seq("colorado", "tornado", "tornadoes")
    val result: SpeechResults = SpeechToTextServices.FileInput.EN_US_Service.recognize(audioFilePath, keywords:_*)

    Then("result is not empty")
    result.getResults.asScala.size should be(1)

    Then("recognized transcript contains textual keywords")
    val bestTranscript = result.getResults
      .asScala.head.getAlternatives
      .asScala.head
      .getTranscript
      .toLowerCase

    keywords.forall(bestTranscript contains _) should be(true)

    Then("keywords are recognized phonetically")
    val realKeywords: Seq[String] = result.getResults.asScala.head.getKeywordsResult.keySet().asScala.toSeq
    realKeywords should contain only("colorado","tornadoes")
  }

}
