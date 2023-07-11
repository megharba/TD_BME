import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait Job {
  def run(args: Array[String]): Unit
}

object MockMain extends Job {
  def run(args: Array[String]): Unit = {
    println("Mock run")
  }
}

class MainSpec extends AnyFlatSpec with Matchers {
  "Main" should "work with run mocked" in {
    val outputStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outputStream) {
      MockMain.run(Array())
    }
    val output = outputStream.toString.trim
    output shouldBe "Mock run"
  }
}