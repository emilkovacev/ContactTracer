package person

abstract class State(person: Person) {
  def toInfected()

  def toRecovered()

  def isInfected: Boolean

}
