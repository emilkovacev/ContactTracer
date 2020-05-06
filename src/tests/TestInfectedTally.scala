package tests

import algorithm.{Edge, WeightedGraph}
import org.scalatest.FunSuite
import person.Person

class TestInfectedTally extends FunSuite {
  test("Tests Infected Tally") {
    // Infected Tally is a method that calculates the instantaneous infected tally of a graph
    // BFS algorithm is used with Dijkstra's to implement tally over time

    // data structure declaration
    val map: WeightedGraph = new WeightedGraph()

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
    map.addEdge(new Edge(0, 6, 7))
    map.addEdge(new Edge(3, 11))
    map.addEdge(new Edge(3, 7))
    map.addEdge(new Edge(11, 7))
    map.addEdge(new Edge(12, 4))
    map.addEdge(new Edge(12, 8))
    map.addEdge(new Edge(8, 1))
    map.addEdge(new Edge(1, 9))
    map.addEdge(new Edge(1, 5))
    map.addEdge(new Edge(5, 9))
    map.addEdge(new Edge(2, 5))
    map.addEdge(new Edge(2, 10))

    // testing

    var tally = map.infectedTally(personA)
    assert(tally("Healthy") == 13)
    assert(tally("Infected") == 0)

    personB.toInfected()
    tally = map.infectedTally(personA)
    assert(tally("Healthy") == 12)
    assert(tally("Infected") == 1)

    personC.toInfected()
    personD.toInfected()
    personE.toInfected()
    personF.toInfected()
    tally = map.infectedTally(personA)
    assert(tally("Healthy") == 8)
    assert(tally("Infected") == 5)

    personG.toInfected()
    personH.toInfected()

    tally = map.infectedTally(personA)
    assert(tally("Healthy") == 6)
    assert(tally("Infected") == 7)

    personA.toInfected()

    personI.toInfected()
    personJ.toInfected()
    personK.toInfected()
    personL.toInfected()
    personM.toInfected()

    tally = map.infectedTally(personA)
    assert(tally("Healthy") == 0)
    assert(tally("Infected") == 13)

    personA.toRecovered()
    personB.toRecovered()

    tally = map.infectedTally(personA)
    assert(tally("Healthy") == 2)
    assert(tally("Infected") == 11)
  }
}
