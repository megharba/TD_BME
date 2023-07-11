package fr.esgi.iabd.data

object Main extends App {

  ArgumentsParser.parse(args) match {
    case Some(arguments) => run(arguments)
    case _               => println("Unable to parse arguments")
  }

  def run(arguments: Arguments): Unit = {
    val data = Data.read(arguments.path)

    if (arguments.gender != None) {
      println(s"Applying filter on gender = ${arguments.gender}")
    }

    println("\nNumber of users:")
    println(data.nbUser(arguments.gender))

    println("\nMean age:")
    println(data.meanAge(arguments.gender))
  }
}
