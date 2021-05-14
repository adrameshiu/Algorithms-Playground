# Breadth-First Search

- explores nodes in `layers`
- systematically explores the edges of G to `discover every vertex` that is reachable from the source `s`.  
- for every vertex `v`, that is reachable from `s`, we only take the shortest path from s to v in terms of the `number of edges(distance)`.

> It is not quite the same as the djikstras algorithm as we find the shortest path based on a cost/weight and not the number of edges.

## Application

- to identify `layers`
- `shortest` path(distance of child=distance of parent+1)

## Approach

- we discover all vertices at a `distance k` from `s` before discovering any vertices at a distance `k+1`
- To keep track of BFS, we color the vertices 3 ways
  - **White** -> undiscovered
  - **Grey**
  - **Black**
- all vertices adjacent to a black vertex will also be discovered(black or grey).
- we start with the root and see adjacent vertices
- whenever we encounter a white vertex v for source u, both vertex v and edge u->v will be added to the bfs-tree
- `u` is considered the `predecessor/parent` of `v` 
  - So if we go from `s->u->v`,
    - s is the **root**
    - u is the **ancestor** of v
    - v is the **descendent** of u

> Since a vertex can be discovered at most once, it can have one parent only

## Algorithm

- add source to queue
- execute loop when queue is not empty
  - find vertices connected to sources from adjacency
  - loop over them and add them to queue if they are undiscovered

## Time Complexity

- O(n+m)