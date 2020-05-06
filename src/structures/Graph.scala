package structures

class Graph[A] {

  var nodes: Map[Int, A] = Map()
  var adjacencyList: Map[Int, List[Int]] = Map()


  def addNode(index: Int, a: A): Unit = {
    nodes += index -> a
    adjacencyList += index -> List()
  }

  def addEdge(index1: Int, index2: Int): Unit = {
    adjacencyList += index1 -> (index2 :: adjacencyList(index1))
    adjacencyList += index2 -> (index1 :: adjacencyList(index2))
  }

  def connected(index1: Int, index2: Int): Boolean = {
    adjacencyList(index1).contains(index2)
  }

  def isPath(path: List[Int]): Boolean = {
    // initialize prev to an invalid node id
    var prev = nodes.keys.min - 1
    var valid = true
    for (index <- path) {
      if (prev != nodes.keys.min - 1) {
        if (!connected(prev, index)) {
          valid = false
        }
      }
      prev = index
    }
    valid
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
      for (node <- this.adjacencyList(nodeToExplore)) {
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
    println("starting with: " + nodes(startID))

    var distance: Map[Int, Int] = Map()
    distance += startID -> 0

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()

      for (node <- adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          println("exploring: " + nodes(node))
          distance += node -> (distance(nodeToExplore) + 1)
          println(distance)
          if (node == index2) {
            return distance(index2)
          }
          toExplore.enqueue(node)
          explored = explored + node
          println(explored)
        }
      }
    }
    0
  }

  def paths(index1: Int, index2: Int): List[A] = {
    val startID: Int = index1
    var explored: Set[Int] = Set(startID)
    println("starting with: " + nodes(startID))

    var distance: Map[A, A] = Map()
    distance += (nodes(startID) -> nodes(startID))

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          println("exploring: " + nodes(node))
          println("from: " + nodes(nodeToExplore))
          distance += (nodes(node) -> nodes(nodeToExplore))
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }

    var currentPath: List[A] = List(nodes(index2))
    var exploring: A = nodes(index2)
    while (exploring != nodes(index1)) {
      exploring = distance(exploring)
      currentPath = exploring +: currentPath
    }

    currentPath
  }
}
