package fr.esgi.iabd.data

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DataSpec extends AnyFlatSpec with Matchers {

     "Data" should "Without Gender filter calcul number of users " in {
    val rows = Vector(
      Row("1", "m", 35),
      Row("2", "f", 78),
      Row("3", "m", 23),
      Row("4", "m", 30)
    )
    val data = Data(rows)

    val nbUsers = data.nbUser()

    nbUsers should be(4)
  }
  "Data" should "With gender filter calcul number of users " in {
    val rows = Vector(
      Row("1", "m", 35),
      Row("2", "f", 78),
      Row("3", "m", 23),
      Row("4", "m", 30)
    )
    val data = Data(rows)

    val nbUsers = data.nbUser(Some("m"))

    nbUsers should be(3)
  }
   "Data" should "without gender filter calcul mean age of all users " in {
    val rows = Vector(
      Row("1", "m", 25),
      Row("2", "f", 40),
      Row("3", "m", 30),
      Row("4", "m", 10)
    )
    val data = Data(rows)

    val meanAge = data.meanAge()

    meanAge should be(Some(26.0))
  }

  it should "With gender filter calcul mean age of users" in {
    val rows = Vector(
      Row("1", "m", 35),
      Row("2", "f", 78),
      Row("3", "m", 23),
      Row("4", "m", 30)
    )
    val data = Data(rows)

    val meanAge = data.meanAge(Some("m"))

    meanAge should be(Some(29.0))
  }

  it should "In case there are no users matching the gender filter, return None for the mean age." in {
    val rows = Vector(
      Row("1", "m", 35),
      Row("2", "f", 78),
      Row("3", "m", 23),
      Row("4", "m", 30)
    )
    val data = Data(rows)

    val meanAge = data.meanAge(Some("u"))

    meanAge should be(None)
  }
  it should "parse lines correctly" in {
  val lines = Vector(
    "1,m,35",
    "2,f,78",
    "3,m,23",
    "4,m,30",
  )
  val expectedData = Data(
    Vector(
      Row("1", "m", 35),
      Row("2", "f", 78),
      Row("3", "m", 23),
      Row("4", "m", 30)
    )
  )

  val parsedData = Data.parse(lines)

  parsedData should be(expectedData)
}

}