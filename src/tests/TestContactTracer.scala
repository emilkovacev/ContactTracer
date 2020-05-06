package tests

import algorithm._
import org.scalatest.FunSuite
import person.Person
import simulation.ContactTracer

class TestContactTracer extends FunSuite {
  test("Tests Contact Tracer Functionality 01") {
    val graph: ContactTracer = new ContactTracer

    val ridwan: Person = new Person("ridwan")
    val joseph: Person = new Person("joseph")
    val josie: Person = new Person("josie")
    val emil: Person = new Person("emil")
    val shyam: Person = new Person("shyam")
    val eliana: Person = new Person("eliana")
    val john: Person = new Person("john")

    graph.addNode(0, emil)
    graph.addNode(1, joseph)
    graph.addNode(2, josie)
    graph.addNode(3, ridwan)
    graph.addNode(4, shyam)
    graph.addNode(5, eliana)
    graph.addNode(6, john)

    graph.addEdge(new Edge(0, 1, 5))
    graph.addEdge(new Edge(0, 2, 2))
    graph.addEdge(new Edge(0, 3, 3))
    graph.addEdge(new Edge(1, 3, 1))
    graph.addEdge(new Edge(2, 4, 7))
    graph.addEdge(new Edge(3, 4, 4))
    graph.addEdge(new Edge(5, 6, 5))

    graph.idealTracer(emil)
    println(graph.infectionDays.map(_._1.name))
    println(graph.infectionDays.values.toList)

    println(graph.findTally(graph.infectionDays))
  }

  test("Tests Contact Tracer Functionality 02") {
    val graph: ContactTracer = new ContactTracer

    val ridwan: Person = new Person("ridwan")
    val joseph: Person = new Person("joseph")
    val josie: Person = new Person("josie")
    val emil: Person = new Person("emil")
    val shyam: Person = new Person("shyam")
    val eliana: Person = new Person("eliana")
    val john: Person = new Person("john")

    graph.addNode(0, emil)
    graph.addNode(1, joseph)
    graph.addNode(2, josie)
    graph.addNode(3, ridwan)
    graph.addNode(4, shyam)
    graph.addNode(5, eliana)
    graph.addNode(6, john)

    graph.addEdge(new Edge(0, 1, 5))
    graph.addEdge(new Edge(0, 2, 8))
    graph.addEdge(new Edge(0, 3, 7))
    graph.addEdge(new Edge(1, 3, 1))
    graph.addEdge(new Edge(2, 4, 1))
    graph.addEdge(new Edge(3, 4, 1))
    graph.addEdge(new Edge(5, 6, 2))

//    graph.completeTracer(joseph)
//    println(graph.infectionDays.map(_._1.name))
//    println(graph.infectionDays.values.toList)
  }

  test("Tests Contact Tracer Functionality 03") {
    val graph: ContactTracer = new ContactTracer

    val ridwan: Person = new Person("ridwan")
    val joseph: Person = new Person("joseph")
    val josie: Person = new Person("josie")
    val emil: Person = new Person("emil")
    val shyam: Person = new Person("shyam")
    val eliana: Person = new Person("eliana")
    val john: Person = new Person("john")

    graph.addNode(0, emil)
    graph.addNode(1, joseph)
    graph.addNode(2, josie)
    graph.addNode(3, ridwan)
    graph.addNode(4, shyam)
    graph.addNode(5, eliana)
    graph.addNode(6, john)

    graph.addEdge(new Edge(0, 1, 5))
    graph.addEdge(new Edge(0, 2, 8))
    graph.addEdge(new Edge(0, 3, 7))
    graph.addEdge(new Edge(1, 3, 1))
    graph.addEdge(new Edge(2, 4, 1))
    graph.addEdge(new Edge(3, 4, 1))
    graph.addEdge(new Edge(5, 6, 2))

    val a = graph.idealTracer(joseph)
    println(graph.findTally(a))

    println(graph.findDayTally(2, joseph))
  }

}
