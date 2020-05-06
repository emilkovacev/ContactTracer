package person

class Infected(person: Person) extends State(person: Person) {
//  person.infectionProbability = 1.0

  override def toInfected(): Unit = {
//    println(person.name + " is already infected!")
  }

  override def toRecovered(): Unit = {
    person.state = new Recovered(person)
//    println(person.name + " recovered")
  }

  override def isInfected: Boolean = {
    true
  }
}
