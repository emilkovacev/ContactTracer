package tests

import algorithm.{Edge, WeightedGraph}
import org.scalatest.FunSuite
import person.{Person, PopulationGenerator}
import simulation.ContactTracer

class TestDijkstra extends FunSuite {
  test("Tests Dijkstra's Algorithm") {
    val map: ContactTracer = new ContactTracer()

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
    map.addEdge(new Edge(2, 5, 5))
    map.addEdge(new Edge(2, 10, 3))

    // testing

    val mapping: Map[Person, Double] = map.dijkstra(personA)
    assert(mapping(personA) == 0)

    assert(mapping(personE) == 3)
    assert(mapping(personD) == 2)
    assert(mapping(personG) == 7)

    assert(mapping(personH) == 7)

    assert(mapping(personI) == 6)

    map.idealTracer(personA)
    println(map.infectionDays.map(_._1.name))
    println(map.infectionDays.values.toList)

    println(map.findTally(map.infectionDays))
  }
}
