package algorithm

import person.Person

object WeightedGraphExample {

  def main(args: Array[String]): Unit = {
    val graph: WeightedGraph = new WeightedGraph

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

//    println(graph.adjacencyList.keys.toList)
//    graph.adjacencyList.values.foreach(_.printConnections())
//
//    println(graph.areConnected(1, 4))
//    println(graph.infectedProbability(0, 4))

//    println(graph.paths(0, 4).map(_.name))

    println(graph.dijkstra(emil))
  }

}
