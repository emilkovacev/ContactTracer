package algorithm

import person.{Healthy, Person}
import structures.Queue

class WeightedGraph(var spreadRate: Double = 0.05) {

  var nodeToPerson: Map[Int, Person] = Map()
  var personToNode: Map[Person, Int] = Map()
  var adjacencyList: Map[Int, Connections] = Map()

  def addNode(index: Int, value: Person): Unit = {
    nodeToPerson += index -> value
    personToNode += value -> index
    adjacencyList += index -> new Connections()
  }

  def addEdge(edge: Edge): Unit = {
    adjacencyList += edge.index1 -> adjacencyList(edge.index1).prepend(new Edge(edge.index1, edge.index2, edge.weight))
    adjacencyList += edge.index2 -> adjacencyList(edge.index2).prepend(new Edge(edge.index2, edge.index1, edge.weight))
  }

  def connected(index1: Int, index2: Int): Boolean = {
    adjacencyList(index1).contains(new Edge(index1, index2))
  }

  def areConnected(index1: Int, index2: Int): Boolean = {
    val startID: Int = index1
    var explored: Set[Int] = Set(startID)

    var distance: Map[Int, Int] = Map()
    distance += startID -> -1

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (edge <- this.adjacencyList(nodeToExplore).edges) {
        val node = edge.index2
        if(!explored.contains(node)){
          distance += node -> nodeToExplore
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }
    if (explored.contains(index1) && explored.contains(index2)) {
      true
    }
    else {
      false
    }
  }

  def distance(index1: Int, index2: Int): Int = {
    val startID: Int = index1
    var explored: Set[Int] = Set(startID)
//    println("starting with: " + nodeToPerson(startID))

    var distance: Map[Int, Int] = Map()
    distance += startID -> 0

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()

      for (edge <- adjacencyList(nodeToExplore).edges) {
        val node = edge.index2
        if(!explored.contains(node)){
//          println("exploring: " + nodeToPerson(node))
          distance += node -> (distance(nodeToExplore) + 1)
//          println(distance)
          if (node == index2) {
            return distance(index2)
          }
          toExplore.enqueue(node)
          explored = explored + node
//          println(explored)
        }
      }
    }
    -1
  }

  def weightedDistance(index1: Int): Map[Int, Double] = {
    val startID: Int = index1
    var explored: Set[Int] = Set(startID)

    var distance: Map[Int, Int] = Map()
    distance += startID -> 0

    var weights: Map[Int, Double] = Map()
    weights += startID -> 1.0

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()

      for (edge <- adjacencyList(nodeToExplore).edges) {
        val node = edge.index2
        if(!explored.contains(node)){
          weights += node -> (weights(nodeToExplore) * edge.weight)
          distance += node -> (distance(nodeToExplore) + 1)
//          println(distance)
          toExplore.enqueue(node)
          explored = explored + node
//          println(explored)
        }
      }
    }
    weights
  }

  def paths(index1: Int, index2: Int): List[Person] = {

    var day = 0

    val startID: Int = index1
    var explored: Set[Int] = Set(startID)
//    println("starting with: " + nodeToPerson(startID))

    var distance: Map[Person, Person] = Map()
    distance += (nodeToPerson(startID) -> nodeToPerson(startID))

    var weights: Map[Int, Double] = Map()
    weights += startID -> 1.0

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      day += 1
//      println("Day 1 --------------------")
      val nodeToExplore = toExplore.dequeue()
      for (edge <- adjacencyList(nodeToExplore).edges) {
        val node = edge.index2
        if(!explored.contains(node)){
          nodeToPerson(node).toInfected()
          distance += (nodeToPerson(node) -> nodeToPerson(nodeToExplore))
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }

    var currentPath: List[Person] = List(nodeToPerson(index2))
    var exploring: Person = nodeToPerson(index2)
    while (exploring != nodeToPerson(index1)) {
//      println(distance.toList)
      exploring = distance(exploring)
      currentPath = exploring +: currentPath
    }

    currentPath
  }

  // CerealGuy69 (explained the pseudo-code to me)
  // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
  // https://www.youtube.com/watch?v=GazC3A4OQTE - Computerphile video on Dijkstra's Algorithm
  // http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/11-Graph/weighted.html - explanation of weighted graphs
  // (I implemented them slightly differently according to my needs)

  def dijkstra(patient0: Person): Map[Person, Double] = {

    patient0.toInfected()

    var days: Map[Person, Int] = Map()
    var day: Int = 0

    val index1 = personToNode(patient0)
    var distance: Map[Person, Double] = Map()
    var previous: Map[Person, Double] = Map()
    for (vertex <- nodeToPerson.values) {
      distance += vertex -> 100
      previous += vertex -> 100
    }
    distance += nodeToPerson(index1) -> 0
    var allNodes: List[Person] = nodeToPerson.values.toList

    while (allNodes.nonEmpty) {
      val u: Person = allNodes.sortWith(distance(_) < distance(_)).head
      allNodes = allNodes.filter(!_.equals(u))

      day += 1

      for (neighbor <- adjacencyList(personToNode(u)).edges) {
        val edgeDistance: Double = neighbor.weight
        val v: Person = nodeToPerson(neighbor.index2)
        val alt: Double = distance(u) + edgeDistance

        v.toInfected()
        days += v -> Math.floor(day).toInt

        if (alt < distance(v)) {
          distance += v -> alt
          previous += v -> personToNode(u)
        }
      }
    }
//    println(days)
    distance
  }

  def infectedProbability(patient0: Person, person: Person): Double = {
    println("spread-rate: " + spreadRate.toString)
    println("p: " + person.infectionProbability.toString)
    println("distance: " + dijkstra(patient0)(person).toString)
    // https://en.wikipedia.org/wiki/Sigmoid_function
    // I'm using Sigmoid here to ensure that any probability I get fits within the range 0 and 1, no matter how large
    val sigmoid: Double => Double = (x: Double) => (1.0 / (1.0 + (Math.pow(Math.E, -1.0 * x))))

    if (patient0 == person) {
      println(1.0)
      println("--------------------")
      1.0
    }
    else {
      // Calculated by probability * spreadrate / distance
      val p = sigmoid(person.infectionProbability * 100 * spreadRate / (dijkstra(patient0)(person)))
      println(p)
      println("--------------------")
      p
    }
  }

  def infectedTally(patient0: Person): Map[String, Int] = {

    val startID = personToNode(patient0)

    var tally: Map[String, Int] = Map("Healthy" -> 0, "Infected" -> 0)
    if (patient0.isInfected) {
      tally += "Infected" -> 1
    }
    else {
      tally += "Healthy" -> 1
    }

    var explored: Set[Int] = Set(startID)
//    println("starting with: " + nodeToPerson(startID))

    var distance: Map[Person, Person] = Map()
    distance += (nodeToPerson(startID) -> nodeToPerson(startID))

    var weights: Map[Int, Double] = Map()
    weights += startID -> 1.0

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (edge <- adjacencyList(nodeToExplore).edges) {
        val node = edge.index2
        if(!explored.contains(node)){
//          println("exploring: " + nodeToPerson(node))
          if (nodeToPerson(node).isInfected) {
            tally += "Infected" -> (tally("Infected") + 1)
          }
          else {
            tally += "Healthy" -> (tally("Healthy") + 1)
          }

          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }

    tally
  }

}
