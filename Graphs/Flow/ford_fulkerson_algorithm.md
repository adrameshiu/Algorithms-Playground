# Ford Fulkerson Algorithm

## What is an Augmenting Path?

- a path of edges in the `residual graph`  from source to sink
- we can find these paths by any method (DFS, BFS etc)
- with `unused capacity greater than 0`
  - we can increase the forward edges(not full)
  - can decrease on backward edges(not empty)

> Each path has a bottleneck -> smallest edge along the path

## Process

- start with **0 flow** everywhere(*Initialization*)
- based on `bottlenecks of each path(min capacity)`, keep finding all augmenting paths
- we also have backward edge between the vertices..this is useful to remove the extra flow
- otherwise, once a flow is set, it never changes-> we will not be able to get optimal flow because sometimes that could mean we dont want the entire water to flow through a certain path
- we `go through all possible paths from s to t`, one augmenting path at a time...based on bottleneck edge `increase capacity through forward edges`...and `decrease and readjust through the other paths `from a vertex that has a backward edge
- algorithm `terminates `when we can no longer find paths from s to t-> ie. when `every forward edge is full` and every `backward edge is empty` 

> we basically add flow to forward edges and remove flow from backward edges

-

## Implementation

### Residual Capacity of a flow

- for a `forward edge` -> we can still pass `capacity-flow`
- for a `backward edge` -> we can pass `flow`(this will be reverse direction)

> So instead of having capacity as weights, in the residual network, we have two edges between each pair of vertices -> forward and backward edge



## How to calculate Max flow?

- it can be considered as the total incoming value at the target
- or can be also seen as the sum of all bottlenecks we found in each augmenting path