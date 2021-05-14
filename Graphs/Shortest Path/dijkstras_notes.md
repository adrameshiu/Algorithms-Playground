# Dijkstra's

### Constraints

- Graph must only contain non negative edge weights
- This is imposed because once a node is visited, its distance cannot be improved. With negative weights, we cant ensure this greedy behaviour 

### Greedy Property

> Always select the next most promising node

### Algorithm

- maintain a priority queue of key,value pairs containing index and distance.

- Insert the source and loop while the priority queue is not empty but only popping the most promising pair each time(priority queue is set as a min heap)

- every time we find a better (key,value) distance pair(key of which could already be present), we add it to the queue

  - > Ideally, we should do a decreaseKey/updateKey operation to update the key if we find a better distance if possible
    >
    > But inserting new value is O(log n). 
    >
    > But searching FOR A KEY and then updating is O(n). It is like this because the priority queue is sorted based on the values and not the keys. So we'll have to find the key one by one linearly. 
    >
    > So directly inserting is a little faster because it'll come up first when the priority queue arranges based on priority

> We can use an **Indexed Priority Queue** for this to optimize this Dijkstra's algorithm that will ensure we don't have duplicate keys and give us a faster implementation.



### what is the problem with negative weights?

If we have a path between 3 nodes in a cycle 

A-(4)->B

B-(-6)->D

D-(2)->C

C-(3)->B

Min delta(min distance)Weight from B to D will be -6.

Now once we go through the cycle(BCD), it become -6 + 2 +3 = -1. So the path because of the cycle caused the weight to drop

IF we reach A->B first time we get a wieght of 4.

Which should ideally be the best weight

Now we go through the cycle and end up with a weight of 4 -1 =3 which is not right. So we just end up decreasing the weight again-> WRONG

## Future Scope

> Look into D-ary heaps for dense graphs, where there are much more updateKey operations required(which are faster in d-ary), than remove min key(which are a little lesser) so not as significant



## DS for Dijkstras

The most general version of Dijkstra's algorithm assumes that you have access to some sort of priority queue structure that supports the following operations:

- make-heap(s, n): build a heap of n nodes at initial distance ∞, except for the start node s, which has distance 0.
- dequeue-min(): remove and return the element with the lowest priority.
- decrease-key(obj, key): given an existing object *obj* in the priority queue, reduce its priority to the level given by *key*.

Dijkstra's algorithm's requires one call to make-heap, O(n) calls to dequeue-min, and O(m) calls to decrease-key, where n is the number of nodes and m is the number of edges. The overall runtime can actually be given as O(Tm-h + nTdeq + mTd-k), where Tm-h, Tdeq, and Td-k are the average (amortized) costs of doing an make-heap, a dequeue, and a decrease-key, respectively.

Now, let's suppose that your priority queue is a doubly-linked list. There's actually several ways you could use a doubly-linked list as a priority queue: you could keep the nodes sorted by distance, or you could keep them in unsorted order. Let's consider each of these.

In a sorted doubly-linked list, the cost of doing a make-heap is O(n): just insert the start node followed by n - 1 other nodes at distance infinity. The cost of doing a dequeue-min is O(1): just delete the first element. However, the cost of doing a decrease-key is O(n), since if you need to change a node's priority, you may have to move it, and you can't find where to move it without (in the worst case) doing a linear scan over the nodes. This means that the runtime will be O(n + n + nm) = O(mn).

In an unsorted doubly-linked list, the cost of doing a make-heap is still O(n) because you need to create n different nodes. The cost of a dequeue-min is now O(n) because you have to do a linear scan over all the nodes in the list to find the minimum value. However, the cost of a decrease-key is now O(1), since you can just update the node's key in-place. This means that the runtime is O(n + n2 + m) = O(n2 + m) = O(n2), since the number of edges is never more than O(n2). This is an improvement from before.

With a binary heap, the cost of doing a make-heap is O(n) if you use the standard linear-time heapify algorithm. The cost of doing a dequeue is O(log n), and the cost of doing a decrease-key is O(log n) as well (just bubble the element up until it's in the right place). This means that the runtime of Dijkstra's algorithm with a binary heap is O(n + n log n + m log n) = O(m log n), since if the graph is connected we'll have that m ≥ n.

You can do even better with a Fibonacci heap, in an asymptotic sense. It's a specialized priority queue invented specifically to make Dijkstra's algorithm fast. It can do a make-heap in time O(n), a dequeue-min in time O(log n), and a decrease-key in (amortized) O(1) time. This makes the runtime of Dijkstra's algorithm O(n + n log n + m) = O(m + n log n), though in practice the constant factors make Fibonacci heaps slower than binary heaps.

So there you have it! The different priority queues really do make a difference. It's interesting to see how "Dijkstra's algorithm" is more of a *family* of algorithms than a single algorithm, since the choice of data structure is so critical to the algorithm running quickly.