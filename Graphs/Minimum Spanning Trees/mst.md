# Minimum Spanning Tree

On a `undirected graph`, a MST is a 

- `subset of the edges` that 
- `connects all vertices` 
- while `minimizing total edge costs`.

> It is a type of greedy algorithm-> locally best-> right now seems like the best thing to do
>
> can be done in DP -> but that is exponential -> so we use greedy which gives polynomial

> For `directed graphs`, look for `optimal branching problem`

## Key Goal

Connect a bunch of points together as cheaply as possible

## Applications

- clustering
- networking

## Time Complexity 

- intuition wise -> this is just a little more than linear time
- just reading a graph itself is linear time
- so this is very fast
- it basically means we are able to find the best tree without even going through all trees

- Prims -> O(E log E) or O(E log V) best case -> similar to dijstra



## Prim's Minimum Spanning Tree

-  best suited for `dense graphs`(lots of edges)
- meets or improves Kruskal's time complexity
- but has trouble finding the ` minimum spanning forest` on a `disconnected graph`
- `Invariance`: maintains connectivity of sub graph

### Algorithm

- maintain a priority queue that sorts edges based on min edge costs
- this will be used to determine next node to visit
- Start on any node->mark as visited, iterate over edges of s adding them to the queue
- while Q is not empty and the mst is not formed yet, dequeue next cheapest edge. If already visited, then skip

> It is similar to djikstras in that we find the min distance/weight
>
> But in djikstras, we see it as the min distance from the source.
>
> Here we just take the min weight from the neighbors currently

We use the lemma that if there is a edge e between u and v, we can remove the edge e by collapsing/merging the two nodes to one. so that all the edges that are connected from u and v will come from this one node

reduces the nodes and edges by 1

with this we can see that we just keep collapsing vertices together making our lemma bigger and bigger



> Greedy Choice -> Each time we collapse a node, greed choice property is that we find the shortest weight of edge e from it and continue



## Kruskal Algorithm

it is useful when weights are integers-> we can use radix sort

- select least costly edge till we reach all vertices
- does not check connectivity at all
- parallelly grows tree and coalesce at the end



> Globally min weight edge will be the min edge that crosses a cut..so it will be part of the mst. so this is the greedy choice

- But we also need to ensure that u and v are not already connected to each other
- we can do this using the union find data structure that maintains connected components

## Algorithm

- make set for all vertices
- sort edges by weights
- loop over edges
- add edge if end points are not in the same component-> if they don't have the same representative(they don't belong to the same connected component)
- and then merge
- union find can be solved in alpha(n) time -> inverse Ackerman time
- so running time depends on sort -> O(nm)