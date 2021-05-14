# Shortest Paths

- In breadth first search,`each edge` counts as a `distance of 1`
- Now, lets look at Graphs that use a different `weight` than distance(which will be a real number)

> Notation -> w(u,v) or w(e) represents the weight of the edge



## How do we find the weight of a path?

- We calculate the weight of the path as the **sum of the weights** of the edges it traversed to reach there

## Motivations

- internet packet routing
- driving routes
- minimizing flight duration

### Where could we see negative weights?

- Where we get paid for something and we might have to pay for something
- Social networks, where we can like or dislike

## Problem Statement

Finding shortest path from a `single source vertex` to `every other vertex in the graph`



## Time Complexity

> Time Complexity doesn't really depend on weights. So the dynamic range of weights can be anything

- Bellman Ford -> O(V*E)
- Dijkstra's -> O(E * log(V)) or O(V * log V + E)



Upper bound of number of edges, will be an edge between all vertices O(V^2)

So bellman ford will be O(V^3) and Dijkstra's around O(V^2)

## Choosing between the two algorithms

So, since the complexity of Dijkstra's is considerably better,  `if there is a possiblity` of `shifting/adjusting` the weights so that they become positive, Dijkstra's is far better



## Relaxation

At first we dont know what is the shortest of all paths becuase we haven't `discovered` any paths yet. 

So we start from the situation where all vertices are at infinite distnces from each other(except source which is at a 0 distance).  And then we select some edge and keep discovering paths.

As we go along the edges, when we find a path between two nodes that is better than the path we already discovered, we `relax` that edge so that we have a shorter distance now.

So we start from infinite distances and we try to reach the min delta weight for each.



## Notions

- We cant just process the nodes/vertices in any order since the order this matter to the complexity. 
- We try to optimize this by identifying that the shortest path from source to target will mean each sub path will also be a shortest path(optimal sub structure)



