package person

class Recovered(person: Person) extends State(person: Person) {
  // in theory, people who come in contact with the virus either gain immunity or die
  // assuming the above is true, recovered people have no chance of getting sick
  person.infectionProbability = 0.0

  override def toInfected(): Unit = {
//    println(person.name + " recovered")
  }

  override def toRecovered(): Unit = {
//    println(person.name + " already recovered!")
  }

  override def isInfected: Boolean = {
    false
  }
}
