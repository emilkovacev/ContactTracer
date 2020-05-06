package simulation

import algorithm.{Infinity, WeightedGraph}
import person.Person

class ContactTracer(spreadRate: Double = 0.005) extends WeightedGraph(spreadRate: Double) {

  var infectionDays: Map[Person, Int] = Map()
  var day: Int = 0
  var totalDistance: Map[Person, Int] = Map()

  def completeTracer(patient0: Person): Map[Person, Double] = {
    val infectedTally: Int = this.infectedTally(patient0)("Infected")
    println(infectedTally)
    if (infectionDays.keys.toList.length >= 5) {
      tracer(patient0)
    }
    else {
      tracer(patient0)
      completeTracer(patient0)
    }

  }

  def tracer(patient0: Person): Map[Person, Double] = {
    val index1 = personToNode(patient0)
    var distance: Map[Person, Double] = Map()
    var previous: Map[Person, Double] = Map()
    for (vertex <- nodeToPerson.values) {
      distance += vertex -> Infinity.size
      previous += vertex -> Infinity.size
    }
    distance += nodeToPerson(index1) -> 0
    var allNodes: List[Person] = nodeToPerson.values.toList

//    println("day 0 ----------")
    patient0.toInfected()
    infectionDays += patient0 -> 0

    while (allNodes.nonEmpty) {
      val u: Person = allNodes.sortWith(distance(_) < distance(_)).head
      allNodes = allNodes.filter(!_.equals(u))

      day += 1
//      println("day " + day + " ----------")

      for (neighbor <- adjacencyList(personToNode(u)).edges) {
        val edgeDistance: Double = neighbor.weight
        val v: Person = nodeToPerson(neighbor.index2)
        val alt: Double = distance(u) + edgeDistance

        if (alt < distance(v)) {
          distance += v -> alt
          previous += v -> personToNode(u)

          if (u.isInfected) {
            val p = scala.util.Random.nextDouble()
//            println("p: " + p.toString)

            if (p < v.infectionProbability) {
//              println("infecting " + v.name + " from " + u.name)
              if (v.isInfected) {
                day -= 1
                v.toInfected()
                infectionDays += v -> day
                day += 1
              }
              else {
                v.toInfected()
                infectionDays += v -> day
              }
            }
          }
        }

        totalDistance += v -> Math.floor(day).toInt

      }
    }
    distance
  }

  def findTally(infected: Map[Person, Int]): Map[Int, Int] = {
    var tally: Map[Int, Int] = Map()

    var lastDay: Int = 0
    for (day <- infected.values.toList.sorted) {
      if (day != Int.MaxValue) {
        if (tally.keys.toList.contains(day)) {
          tally += day -> (tally.getOrElse(day, 0) + 1)
        }
        else {
          tally += day -> (1 + tally.getOrElse(lastDay, 0))
          lastDay = day
        }
      }
    }
    tally
  }

  def findDayTally(day: Int, patient0: Person): Int = {
    val tally = this.findTally(this.idealTracer(patient0))
    println(tally)
    if (day > tally.keys.toList.max) {
      return tally(tally.keys.toList.max)
    }

    var previousVal: Int = 0
    for (i <- tally.keys.toList.sorted) {
      if (i > day) {
        return tally(previousVal)
      }
      else if (i == day) {
        return tally(i)
      }

      previousVal = i
//      println(i)
    }
    tally(0)
  }

  override def infectedProbability(patient0: Person, person: Person): Double = {
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

  def idealTracer(patient0: Person): Map[Person, Int] = {
    val index1 = personToNode(patient0)
    var distance: Map[Person, Int] = Map()
    var previous: Map[Person, Int] = Map()
    for (vertex <- nodeToPerson.values) {
      distance += vertex -> Infinity.size
      previous += vertex -> Infinity.size
    }
    distance += nodeToPerson(index1) -> 0
    var allNodes: List[Person] = nodeToPerson.values.toList

    println("day 0 ----------")
    patient0.toInfected()
    infectionDays += patient0 -> 0

    while (allNodes.nonEmpty) {
      val u: Person = allNodes.sortWith(distance(_) < distance(_)).head
      allNodes = allNodes.filter(!_.equals(u))

      day += 1
      println("day " + day + " ----------")

      for (neighbor <- adjacencyList(personToNode(u)).edges) {
        val edgeDistance: Double = neighbor.weight
        val v: Person = nodeToPerson(neighbor.index2)
        val alt: Int = (distance(u) + edgeDistance).toInt

        if (alt < distance(v)) {
          distance += v -> alt.toInt
          previous += v -> personToNode(u).toInt

          if (u.isInfected) {
            println("infecting " + v.name + " from " + u.name)
//            if (v.isInfected) {
//              day -= 1
//              v.toInfected()
//              infectionDays += v -> day
//              day += 1
//            }
//            else {
              v.toInfected()
              infectionDays += v -> day.toInt
//              }
          }
        }

        totalDistance += v -> Math.floor(day).toInt

      }
    }
    distance
  }

}
