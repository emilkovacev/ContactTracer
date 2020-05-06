package person

object PopulationGenerator {
  def generatePeople(numberOfPeople: Int = 26): Map[Int, Person] = {
    if (numberOfPeople > 26) {
      return Map()
    }
    // https://alvinalexander.com/source-code/scala-sequence-list-all-ascii-printable-characters/
    // used to get a list of ascii values in scala
    val chars = ('A' to 'Z').toList
    val people: Map[Int, Person] = (for (node <- Range(0, numberOfPeople)) yield {
      node -> new Person(chars(node).toString)
    }).toMap
    people
  }
}
