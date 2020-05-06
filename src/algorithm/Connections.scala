package algorithm

class Connections(var edges: List[Edge] = List()) {
  def contains(edge: Edge): Boolean = {
    for (e <- edges) {
      if (e.isEqualTo(edge)) {
        return true
      }
    }
    false
  }

  def printConnections(): Unit = {
    println(edges.map(_.stringify).toString())
  }

  def prepend(edge: Edge): Connections = {
    this.edges = edge :: this.edges
    this
  }
}
