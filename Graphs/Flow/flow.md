# Maximum Flow

- think of water pipe
- each edge has a capacity
- each edge should obey laws of physics
  - cant exceed capacity
  - whatever flows in, needs to flow out

> How much water can flow within the system depends on how much enters the source(and goes to sink)

## Keywords that could help identify use cases

- `bottlenecks`
- `traffic`

## Applications

- traffic through roads
- flow of water through pipes
- amount of current flow in electric circuits 

## Max Flow

- For this network, what is the maximum water that can flow through this system
- real world->see if the flow is optimal-> see if all pipes are being utilized to its capacity-> oterwise change pipes etc





## Figuring out max flow by augmenting paths

- how do we turn a max flow problem into a finding path problem(since we know DFS/BFS)?
- Answer-> using residual flow networks

## Residual Network

- whatever excess capacity we have, we create a network/graph that goes in the reverse direction with capacity=excess of main graph(remaining capacity)
- if the flow is positive, then backward edge of same capacity-> to be able to decrease that flow 

> Why do we have backward edges?
>
> **Answer**: to `undo bad choices` that will not lead to maximum flow



## Ford Fulkerson

- uses `DFS` to find paths 

DFS gives all kinds of paths -> overall time complexity is very costly

- Time complexity depends on which algorithm we use to find these augmenting paths(BFS, DFS?)
- If we use DFS, it is possible we keep going through the most bottlenecked path even if is inefficient
- so the time complexity is a function of the flow and bottlenecks too

> Time Complexity: O(E* f )



## Edmond Karp

- uses `BFS` to find paths
- find shortest/direct paths in terms of `number of edges` or `distance` (similar to looking at it unweighted terms)
- while `exploring neighbors`, we can only reach the node if the `remaining capacity(capacity-flow) of the edge to get there` is `greater than 0`(not a backwawrd edge)

BFS gives the most direct/shortest path - > this will keep checking only shortest each time and then checking for the next distance->reduces the time complexity a lot

>  Time Complexity: O(V * E^2 )
>
> since it doesnt depend on `flow value`, it is called a `strongly polynomial time` algorithm



## Goldberg's Push Relabel

- uses concept of finding `preflow` `instead of finding augmenting paths`

- we wont look at it as source and sink
- think of it like a tree
- start from source flowing downhill
- we want see how much water we can push downhill
- we do this flow incrmentally
- instead of flow, we are talking in terms of buckets of water
- every node has bath tub attached to it(can hold water)->amount of water coming in is more than flowing out(doesnt follow physics)

each node has excess= incoming edge flow

height of node can be 1 greater than min of the neighbors

can only fall from node to neighbours when height is more

so each step we keep increasing the height of the node  so that water flow from node to neighbors

nodes will keep raising height higher and higher incrementally and will reach higher than the source -> at that point we know exactly 

>  Time Complexity: O(E * V^2 )