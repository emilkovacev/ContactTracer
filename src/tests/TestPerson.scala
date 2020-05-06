package tests

import org.scalatest.FunSuite
import person.Person

class TestPerson extends FunSuite {

  val EPSILON: Double = 0.0005

  def equalsDoubles(doubleA: Double, doubleB: Double): Boolean = {
    if (Math.abs(doubleA - doubleB) < EPSILON) {
      true
    }
    else {
      false
    }
  }

  test("Tests Person functionality") {
    val john: Person = new Person("John")

    // tests state: Healthy
    assert(!john.isInfected)

    assert(equalsDoubles(john.infectionProbability, 0.5))

    john.toRecovered()
    assert(!john.isInfected)

    assert(equalsDoubles(john.infectionProbability, 0.5))

    // tests state: Infected
    john.toInfected()
    assert(john.isInfected)

    assert(equalsDoubles(john.infectionProbability, 1.0))

    john.toInfected()
    assert(john.isInfected)

    assert(equalsDoubles(john.infectionProbability, 1.0))

    // tests state: Recovered (not in implementation)
    john.toRecovered()
    assert(equalsDoubles(john.infectionProbability, 0.0))

    john.toInfected()
    assert(!john.isInfected)
    assert(equalsDoubles(john.infectionProbability, 0.0))

    john.toRecovered()
    assert(!john.isInfected)
  }
}
