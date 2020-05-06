package person

class Healthy(person: Person) extends State(person: Person) {

  override def toInfected(): Unit = {
    person.state = new Infected(person)
//    println(person.name + " is infected")
  }

  override def toRecovered(): Unit = {
//    println(person.name + " is still healthy!")
  }

  override def isInfected: Boolean = {
    false
  }
}
