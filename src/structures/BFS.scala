package structures

import structures.Graph

object BFS {

  def bfs[A](graph: Graph[A], startID: Int): Unit = {

    var explored: Set[Int] = Set(startID)
    println("starting with: " + graph.nodes(startID))

    var distance: Map[Int, Int] = Map()
    distance += startID -> -1

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- graph.adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)) {
          println("exploring: " + graph.nodes(node))
          distance += node -> nodeToExplore
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }
  }
}
