# Goldberg's push Relabel Algorithm

## Why push relabel?

- Think of the case when there are good paths from source till a vertex(all of k units) and then the graph splits into multiple paths(each with a capacity of 1 unit)
- paths will be repeated and the ford fulkerson and bellman ford consider each `augmenting path` and time complexity will be a lot.
- Now what if we can push the entire capacity and then see what happens at each stage?
  - we wont have to recompute all these long paths again and again 



## Concepts

### Pre flow

- same as flow 
- conservation constraints (flow in=flow out) are little more relaxed
- when we flood from each level onto next, it wont immediately be balanced, it will only be balanced after the lower level consumes it

Conditions

- non negative and flow <= capacity
- flow in `>=` flow out...meaning more flow can go in but not out of vertex(except at S)

Basically we are checking the best flow `level by level`-> now with this as source, how much can i use 

### Residual Network

- we can still define residual network just as before

### Excess

- ultimately, when source reaches target, we want a `flow` `not a preflow`.
- so at each step, we need a measure of how badly we are violating the flow constraints
- we define that with `excess of vertex` which is the `difference between flow in and flow out`
- flow means -> excess=0

### Height

- For each vertex, we are going to give a height

- **Invariants**

  - Height of `source` is just `n` -> `number of vertices`(does not change)
  - `Sink` is always at height `0` -> never changes
  - at all times in the `residual network`, arcs `cannot go downhill` `too quickly` 
  - can only go `downhill one step`

- If these invariants hold, the residual graph `does not have a path from s to t` at this `current moment of time` (s and t are disconnected)

  - it would have had to pass through total height of the graph to reach the sink
  - if there is a max flow, then there is no other augmenting s-t path, that can have a better flow

  

## Approach

We are only going to push flow along `single edge` along a network `in each step`

So its like broadening the horizon of the sink each time until it becomes the actual sink

>  Termination condition: If we satisfy height invariants and the residual graph invariant, then we are done

#### The other algorithms 

- were like greedy by `each augmenting paths` 
- feasibility of maintaining the flow if there is a path`(invariant)`
- goal that algo was working toward was reaching a graph with path from s to t`(Goal:connecting s and t)`

### Push relabel

- now it is like a greedy by `adjacent vertex`
- now invariants and goals are opposite
- `invariant`: insisting that s and t are disconnected
- `goal` : transforming preflow to flow...`connecting the vertices`

### Functions

#### Push(v)

- we are `at a vertex` and want to `push the excess` water `out of this vertex`
- hoping there is outgoing edge from the vertex
- what if there is no outgoing edge?
- the most we can push is the smaller of two bottlenecks -> `min(excess available, residual capacity of edge)`

But we have to be careful and ensure the excess does not reappear because of a cycle

### Relabel(v)

For cases where is no downhill edges connected to v, there is no flow that can happen...

so we have to find a way for us to be able to adjust the height of the vertex so that we can flow

- increment the height of the edge by 1

### Initialization(height, preflow)

- `height of source` -> we set height of source as n and others as 0
- `preflow at source` -> aim is we cant have any residual edges going out from `s`...we can do that by `passing flow=capacity`(saturate) to `all edges from source`-> this means that `residual graph`                `will not containing` any `outgoing edges from s`
- preflow for all other edges = 0

### Main Loop

- we want to squash excesses..so this loop just looks for vertex with these excesses
- if there are no vertices with excess, we are done
- if there are multiple vertices with excess, pick the one with the max height
- always prioritize pushing -> 
  - if we can push , just push the excess
    - invariant just says things cant go `downhill too quickly`...doesnt say anything about uphill
    - so reverse edge going uphill is fine
  - if not, then `relabel`
    - the only time we are actually relabel the vertices are if the adjacent vertices are flat or go up

## Time Complexity

- lets look at the number of times each operation is invoked
- each time we do a push and each time we do a relabel

> Number of relables :O(n^2)
>
> Number of pushes: O(n^3)

​	**Key lemma:**

​	At any vertex at any height with excess, there must be a path from that vertex to the source in the residual graph so that we can reallocate from there when the height increases above the source height

intuition: water came from source to the vertex ultimately -> if it came from source there must be a way of undoing...bascially telling i dont want that much 

At most any vertex can have a height of h(S) + (n-1) = 2n-1 when source is anchored at n 

Height is increased by 1 each time -> each vertex an be relabels possibly O(n) time

Now for n vertices -> O(n^2) relables

Two diff kind of pushes -> 

 - we can constrained by residual capacity
   	- non saturating push
 - constrained by excess
   	- if edge is constraining, saturating push
   	- this shouldn't be happening too many times
   	- between two saturating pushes between consecutive vertices, there must be some labels at least twice
   	- a vertex can only be relabeled O(n) times, so each time saturating push, there will be two relabels -> O(n) per edge
   	- for overall edges O(n*m) = O(n^3) pushes