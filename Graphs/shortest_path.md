# Shortest Path Algorithms



- based on weights

> Having a negative weight means, shortest path(sum of weights along the path) will be negative, so we keep going on it again and again because the distance is negative. Nonsensical

## Bellmen Ford

- will go through and find these negative loops and gives a signal for the algo to ignore them
- relax all edges in the graph

> Time Complexity :
>
> initialization-> O(n)
>
> relaxation loop -> O(nm)
>
> negative cycle detection
>
> total -> O(nm)-> not that great

### Triangle Inequality

Over approximation and then relaxing our upper bound

We take the shortest path as some upper bound (and not the actual shortest)->so we have a best yet distance. And then we'll keep lowering when we find a better distance

## Dijkstras Algorithm

- cannot handle negative weights
- like BFS, expand wave
- but not as straight forward as BFS as we are going based on weights and not edge count..so even if i just have 1 edge to traverse, if it is having a higher weight like 8, then it is a costly edge and we  could find a better path

> can be intuted as a greedy optimization problem

- use a **prioirty queue(min heap)** to store the potential possible vertices
- pop which ever is smallest and traverse their out edges
- and once we reach the node which already is in the prioirty queue, we will call an update key(decrease key(similar to insert key in max heap))



S -5-> Y -2-> Z -6-> X

​									  |4

​									Z



 S Reaching Y  							Y:5, T:10

Y->Z                                             Z:7, T:10

Update S->T as 5+3 =8           Z:7, T:8



> **Time Complexity:**
>
> n pushes to the queue -> O(n log n)
>
> n pops from the queue -> O(n log n)
>
> m(ie. number of edges in graph) decrease key -> O(m log n)
>
> Total -> O((n+m) log n)
>
> 