# Bipartite Graph/ BiGraphs

- An `undirected` graph is bipartite  
- if we can partition a graph into `left` and `right` (`2 colorable` basically, each edge has one red, one green)
- such that `every edge` has `one vertex in left` and `one vertex in right`

> if we add an `odd length cycle` to a graph, it becomes `non bipartite`

ie, if two vertices are adjacent, 1 vertex is in v1 and other in v2



> When we set up a bipartite graph, we are actually interested in `Maximum Cardinality Bipartite Matching(MCBM)`

## Conditions to be a bipartite graph

- each vertex must be in one of the two sets
- if there is an edge, it must connect between the two sets



## Checking if a graph is bipartite or not(2 coloring problem)

- we try to color the sets differently
- we go through it dfs wise, coloring each vertex as opposite color of previous vertex(to satisfy 2nd condition)
- if even a single node has same color as its child, then it should return false
- if child is already visited, if the parent and child have the same color, then also it means it is not a bipartite graph

## Identifying bipartite graphs

- we can use `max flow` to get this or
- dfs hopcroft-karp augmented paths

## Bottlenecks

- Taking the example of girls willing to be paired up with 1 guy, after they have given in their preferences, we will get a bottleneck if 3 girls collectively only like 2 guys. then we cant find  a pair for one of them 

- For set L(left) and image of the set R(right),  whenever we have set with size of   L is greater than size of R



## How do we solve this?

### Greedy Solution

- similar to what we tried for interneurons
- the first person gets the book he wants. 2nd one will get as long as it doesnt collide and so on
- not optimal!

Make this a max flow problem

### Max Flow problem

- convert into a directed graph 
- and add `1 unit capacity` to each edge with a `flow of 0` `(0/1)`
- now introduce the source(from source to people 1edge each) and the sink nodes(from books to sink 1 edge each)
  	- this edge we hooked up with a `capacity of 1` from source to person ensures that each person gets only 1 book and no more
  	- the number of copies of same book that can be chosen is controlled by `capacity of edges leading to sink`
- use max flow algorithm to push flow through the network
- this will show us what edges get populated with flow
- now when we try to populate based on `any max flow algorithm` to get `max cardinality matching`, we see that some units got populate with 1 unit of flow 



## References

https://www.youtube.com/watch?v=GhjwOiJ4SqU

https://www.youtube.com/watch?v=HZLKDC9OSaQ