package tests

import algorithm.Edge
import org.scalatest.FunSuite
import person.Person
import simulation.ContactTracer

class TestIndividualProbability extends FunSuite {
  test("Tests Individual Illness Probability") {
    val map: ContactTracer = new ContactTracer(0.01)

    val personA: Person = new Person("A")
    val personB: Person = new Person("B")
    val personC: Person = new Person("C")
    val personD: Person = new Person("D")
    val personE: Person = new Person("E")
    val personF: Person = new Person("F")
    val personG: Person = new Person("G")
    val personH: Person = new Person("H")
    val personI: Person = new Person("I")
    val personJ: Person = new Person("J")
    val personK: Person = new Person("K")
    val personL: Person = new Person("L")
    val personM: Person = new Person("M")

    map.addNode(0, personA)
    map.addNode(1, personB)
    map.addNode(2, personC)
    map.addNode(3, personD)
    map.addNode(4, personE)
    map.addNode(5, personF)
    map.addNode(6, personG)
    map.addNode(7, personH)
    map.addNode(8, personI)
    map.addNode(9, personJ)
    map.addNode(10, personK)
    map.addNode(11, personL)
    map.addNode(12, personM)

    map.addEdge(new Edge(0, 3, 2))
    map.addEdge(new Edge(0, 4, 3))
    map.addEdge(new Edge(0, 5, 5))
    map.addEdge(new Edge(0, 6, 7))
    map.addEdge(new Edge(3, 11, 3))
    map.addEdge(new Edge(3, 7, 8))
    map.addEdge(new Edge(11, 7, 2))
    map.addEdge(new Edge(12, 4, 2))
    map.addEdge(new Edge(12, 8, 1))
    map.addEdge(new Edge(8, 1, 1))
    map.addEdge(new Edge(1, 9, 3))
    map.addEdge(new Edge(1, 5, 3))
    map.addEdge(new Edge(5, 9, 1))
    map.addEdge(new Edge(2, 5, 10))
    map.addEdge(new Edge(2, 10, 3))

    map.infectedProbability(personA, personA)
    map.infectedProbability(personA, personF)
    map.infectedProbability(personA, personH)
    map.infectedProbability(personA, personC)

  }
}
