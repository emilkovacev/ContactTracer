package algorithm

class Edge(val index1: Int, val index2: Int, var weight: Double = 0.0) {
  def stringify: String = {
    "(" + index1.toString + ", " + index2.toString + ")"
  }

  def isEqualTo(edge: Edge): Boolean = {
    if (index1 == edge.index1 && index2 == edge.index2) {
      true
    }
    else {
      false
    }
  }

}
