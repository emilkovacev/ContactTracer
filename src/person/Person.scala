package person

class Person(val name: String) {
  var state: State = new Healthy(this)

  var infectionProbability: Double = 0.5

  def toInfected(): Unit = {
    this.state.toInfected()
  }

  def toRecovered(): Unit = {
    this.state.toRecovered()
  }

  def isInfected: Boolean = {
    this.state.isInfected
  }

}
