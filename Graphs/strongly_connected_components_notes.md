# Strongly Connected Components

## What is a strongly connected component?

- maximally connected of graph-> internally strongly connected
- path are mutually reachable and each vertex has a path to itself
- can we call it a cycle?

> Strongly connected components are like fracktals(not necessarily repeating) in a graph. If we identify strongly connected components and zoom out, we get another graph
>
> So if we can go anywhere to anywhere between two strongly connected components, they will be one strongly connected components

## Applications

- should we look at this for grouping cells into classes? -> no..because it is directed, there is usually no path back from interneurons to source

## Approach -> Kosaraju's 2 pass algorithm

- `Depth First Search`
- If we call a dfs, based on the starting point we choose, there is a possibility of discovering all nodes discoverable(as long as there is a path outwards/directed)
- so we could ideally find all strongly connected groups unioned
- `Main takeaway` -> if we call DFS from the right place, we can uncover all SCC's. If we call it from wrong place, we dont get any information of strongly connected components at all
- we also realize that to identify where to call the DFS from so that we get all the strongly connected components, we can use DFS again

## Algorithm

- Find the transpose of the graph(reverse the direction of edges)
- Find the order in which the nodes are traversed  in the **transpose graph** using **Depth First Search**- > A node is visited only when all the edges connecting from it are traced
- Store the order in which the nodes are traversed in a stack(LIFO)
- Pop each element of the stack(so the last visited node will be considered first) and perform **Depth First Search **on the **actual graph**
- Each group of nodes traversed from each root node will give the strongly connected components to that root node.



> Why do we find the transpose?
>
> - it is important to note that SCC in transpose = SCC in main graph
> - because of this, the only thing that changes is the direction of arc between the components(which remember is only one way as otherwise they also would have been strongly connected together)
> - so when we apply DFS in the reverse graph, two things can occur
>   - the dfs node to be traversed appears in the target SCC in reverse graph 
>     - this means we will explore everything in this target SCC component before we see anything in the source SCC(because the graph with SCC is acyclic)
>     - so finishing time of all of the source SCC will be greater than all of target SCC
>   - the dfs node to be traversed appears in the source SCC in reverse graph
>     - now since DFS only finishes when all outgoing nodes are checked, the source in source SCC will finish only after all the target SCC nodes have been checked
>
> Why do we run DFS twice?
>
> - we are basically trying to find those components that have no outgoing arcs(limiting component). So we first find a component, then see the next component and so on. Otherwise we get the first component as part of the 2nd component even thought it is not..so mainly for seggregating components
> - thats why we use sinks and not sources.  as source would have given us everything 
> - so when we start with sinks in the 2nd DFS, its as if we see the leader, everything in that component gets covered  an we never see any of it again
>
> - we do a little bit of bookkeeping(finishing time of the vertices)-> we pass with the decreasing order of this
> - we figured that if the DFS is executed in the correct order, we can get all strongly connected components
> - so the first pass is just to find the best order
> - we use the order in the second DFS using the `leader node` we found

## Useful Resources

https://hassamuddin.com/blog/kosaraju/

https://www.youtube.com/watch?v=z9oOadBgO9I

https://www.youtube.com/watch?v=O98hLTYVN3c&list=PLXFMmlk03Dt7Q0xr1PIAriY5623cKiH7V&index=51