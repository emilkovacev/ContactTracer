CONTACT TRACER
April 30th, 2020


OVERVIEW
A contact tracer is a piece of software intended to track people that may have been infected by a virus by looking at any contacts made between already infected patients. It is intended as a tool to prevent the spread of particularly highly contagious viruses during epidemics.

This program takes in a Weighted Graph containing type Person and implements Dijkstra’s Algorithm to find the most likely path of infections as well as the tally of people infected over time.


IMPLEMENTATION
Nodes A and B are connected by an edge with a weight. That weight represents the distance between Person A and Person B. The closer A is to B, the more likely they are to be infected, and thus, the higher the individual’s rate of infection.
As is seen in the image to the right, the smaller the distance value, the greater the rate of infection. Rate of infection is affected by distance, but also by benign other factors like how often someone washes their hands, how healthy they are, their age, etc. As such, this value can be independently manipulated when a Person is created, however, it is set to 0.5/1.0 by default.

If Person B gets infected, their chance of spreading it to Person A (a nearby connection) is Person A’s infection rate. When the algorithm computes the simulation, it simulates a probability of 0.3 for Person A, and if that probability matches, it infects the person and continues in the algorithm.

As seen above, if one Person shared an edge with a Person in the “Infected” state, they will have a probability of getting infected equal to their infection rate state variable.


DAYS
1 day passes every time every single Person (node) in the Graph that is able to infect a Person attempts to do so. For the following example, refer to the above image. Once Person A infected Person B, they have no moves left, since they’ve infected everyone that they are close to. Since Person A (Patient 0 so to speak) is infected first, the day they are infected is classified as Day 0. Subsequently, when Person B is infected, supposing they are infected the first time, they are infected on Day 1. Since all nodes have attempted to infect all their neighboring nodes, a day passes. On day 2, Person B “attempts” (as in, has a chance) to infect Person C, however, the virus is unsuccessful. Person B has attempted to infect all its non-infected neighbors, a day passes. On day 3, Person B tries to infect Person C again. This time, the virus is strong enough to get to Person C, and Person C gets sick on Day 4. Thus the total mapping of days to infections is as follows:

0: [PersonA], 1: [PersonB], 2: [ ], 3: [Person3]

Using recursion and a combination of BFS and Dijkstra’s, the algorithm stops when every person in the simulation is infected and returns a tally of infected people per day.
