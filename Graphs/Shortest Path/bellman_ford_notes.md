# Bellman Ford Algorithm

- Bellman ford handles the negative weights problem by keeping the condition that the min weight from the same source to same target has a hard limit of 0
- delta weight from (s, s) = 0



- Has multiple passes

  ​	Starts at i=1 goes till |v|-1

  ​	for each pass, it relaxes every edge(this whole pass setup iterates number of vertices times over number of edges)...O(V*E)

- then another loop to check  if -ve cycle exists(loop E times)
- if dist[v] > dist[u] + weight of edge uv, then “Graph contains negative weight cycle”
- The idea of this is, previous step guarantees the shortest distances if the graph doesn’t contain a negative weight cycle. 
- If we iterate through all edges one more time and get a shorter path for any vertex, then there is a negative weight cycle

So the total complexity is O(V*E + E) which comes out to O(V*E)



## Theorem

>  If there are total m vertices, then the number of edges between two vertices cannot be more than m (max m-1 )...otherwise we'll end up with a cycle



We relax each edge in each pass of the algorithm 

By relaxing every edge each time, we are growing the frontier between source and target one node each pass



## Caveats

Bellman ford does well to handle negative weights in polynomial time

But it still wont be able to handle negative cycles. That is still a np- hard problem..cant be solved in less than exponential time

If there is a negative weight cycle, the cycle is reported and the calcualtion is aborted