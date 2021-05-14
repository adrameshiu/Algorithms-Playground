# Graphs

- G = (V,E)
- mathematically represented with a set of vertices V and edge E
- Two types of graphs
  - `Directed Graphs`-> direction matters
  - `Undirected Graphs` -> direction does not matter
- given edge (u,v) in a directed graph, we say v is `adjacent` to u(u->v)

## Graph Representations

edges of a graph can be represented in many ways

#### Adjacency Lists

- edges are represented as an array of linked lists

```
     |0| -> 0
     |1| -> 2 -> 4
     |2| -> 5
     |3| -> 0 -> 5
     |4| -> 2
     |5| -> 4```
```

-  good for storing ***sparse graphs***
  -  graphs where the number of nodes/vertices may be high, but there are very few edges between them

#### Time Complexity

- Space: ***O(n + m)***
- Edge detection given two vertices: ***O(n)*** -> go through to find the source and then trace linked list
- Edge insert: ***O(1)***
- Edge removal given two vertices: ***O(n)*** -> need to find if it is present in any edge 
- Edge remove given edge handle: ***O(1)*** if use double linked
- Edge removal: O(n) or O(1) if use double linked and edge handle
- Vertex insert: amortized ***O(1)*** using table doubling
- Vertex delete: not easily supported
- Visit the out-edges (adjacent) of a vertex: ***O(n)***
  but, on average ***O(1) for a sparse graph***.

### Adjacency Matrix

- edges are represented as 1 in a edge matrix
- good for **dense graphs**
  - lots of interlinked edges

##### Time Complexity

* Space: **O(n²)**-> 2D matrix
* Edge detection given two vertices: ***O(1)***-> just pass index to matrix
* Edge insert: **O(1)**
* Edge removal given two vertices: **O(1)**
* Edge remove given edge handle: **O(1)**
* Edge removal: O(1)
* Vertex insert: amortized **O(1)** table doubling
* Vertex delete: not easily supported
* Visit the out-edges (adjacent) of a vertex: **O(n)**

## Searching Through a graph

## Breadth-First Search (BFS)

- helps find shortest path
- we go in steps incrementing distance each time and traversing all edges around it

- if we look at this in the form of a list, for each vertex v, we store previous vertex in its shortest path as `parent`
- we store vertices already encountered with an array called `visited`

`The Above part was handled differently in the code`

> CAVEATS:
>
> might require  more storage if the graph has many high degree(number of out edges at vertex)-> will make the queue big



## Depth-First Search (DFS)

- we go deeper at each step, following an out edge till we reach  stage where there are no out edges(which also have not been visited)
- We then backtrace and check if there are other out edges we can visit or not

### Applications

- maze solver
- topological sort
- finding strongly connected components

## Approach

- instead of a queue(FIFO), we use a `stack`(LIFO)

> CAVEATS:
>
> if the graph contains long paths, then DFS uses a lot of storage for recursion stack
>
> so, for infinitely long paths, DFS is not a good choice