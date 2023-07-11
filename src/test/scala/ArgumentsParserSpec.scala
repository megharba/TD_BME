package fr.esgi.iabd.data

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ArgumentsParserSpec extends AnyFlatSpec with Matchers {
     
 "ArgumentsParser" should "Parse arguments correct" in {
    val args = Array("src/main/resources/data1.csv", "--gender", "m")
    val expectedArguments = Some(Arguments("src/main/resources/data1.csv", Some("m")))

    val parsedArguments = ArgumentsParser.parse(args)

    parsedArguments should be(expectedArguments)
  }
   it should "Invalid gender argument x" in {
    val args = Array("src/main/resources/data1.csv", "--gender", "x")
    val expectedArguments = None

    val parsedArguments = ArgumentsParser.parse(args)

    parsedArguments should be(expectedArguments)
  }
  
  it should "Missing gender argument None" in {
    val args = Array("src/main/resources/data1.csv")
    val expectedArguments = Some(Arguments("src/main/resources/data1.csv", None))

    val parsedArguments = ArgumentsParser.parse(args)

    parsedArguments should be(expectedArguments)
  }
  
 
  it should "validate gender argument x" in {
  val args = Array("src/main/resources/data1.csv", "--gender", "x")
  val expectedArguments = None

  val parsedArguments = ArgumentsParser.parse(args)

  parsedArguments should be(expectedArguments)
}
it should "print 'Error to parse arguments' : invalid arguments" in {
  val outputStream = new java.io.ByteArrayOutputStream()
  Console.withOut(outputStream) {
    ArgumentsParser.parse(Array("--invalid")) match {
      case Some(_) => fail("Expected None, but got Some")
      case None    => println("Error to parse arguments")
    }
  }
  val output = outputStream.toString.trim
  output shouldBe "Error to parse arguments"
}

 "parse" should "Incorrect arguments return None" in {
    val args = Array("--invalid")
    val result = ArgumentsParser.parse(args)
    result should be(None)
  }

  it should "Incorrect file extension return None argument" in {
    val args = Array("--path", "data.txt")
    val result = ArgumentsParser.parse(args)
    result should be(None)
  }

  it should "Non-existent file return None" in {
    val args = Array("--path", "nonexistent.csv")
    val result = ArgumentsParser.parse(args)
    result should be(None)
  }
  it should "Missing file path return None" in {
  val args = Array("--gender", "m")
  val result = ArgumentsParser.parse(args)
  result should be(None)
  
}

}


 

