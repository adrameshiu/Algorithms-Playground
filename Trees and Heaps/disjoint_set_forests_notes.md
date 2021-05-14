# Disjoint Sets

- Some applications involve **grouping n distinct elements** into a collection of disjoint sets
- These applications often need to perform two operations-
  - finding the unique set that contains a given element
  - uniting two sets

## What is a disjoint data structure?

- it maintains a collection of disjoint dynamic sets {S1,S2....Sn}
- We identify each set by a **Representative**(some member of each of the sets)
- we represent each element of a set by an object

## Required Operations to be supported

- **MAKE_SET**
  - creates a new set whose only member(and thus representative) is x
  - Since the sets are disjoint, we require that x not already be in some other set.
- **UNION**
  - unites the dynamic sets that contain x and y(Say Sx and Sy) into a new set that is the union of the two sets
  - Since the sets are disjoint, performing a union, reduces the size by 1
  - If there are n make set operations(we have n sets), we can at most perform n-1 unions
  - we perform UNION(x,y) by appending y’s list onto the end of x’s list.
  - The representative of x’s list becomes the representative of the resulting set
  - We use the tail pointer for x’s list to quickly find where to append y’s list.
  - we must update the pointer to the set object for each object originally on y’s list, which takes time linear in the length of y’s list.
  - 
- **FIND_SET**
  - returns a pointer to the representative of the unique set containing x

## Finding Connected components in a undirected graph

- Each vertex of the graph is first placed into its own set using make_Set
- then we use find set to find the pointer to the linked lists of each set
  - if the two sets are the same, then found same component.
  - if they are different, then call union



- Each set is represented by its own linked list
- The object(linked list) for EACH SET has
  - an attribute ***head\*** pointing to the first object in the list
  - and ***tail\*** pointing to last
- Each object in the linked list for each set will have
  - a set member
  - pointer to the next object in the list
  - pointer back to the set object
- Within each linked list, member objects may appear in any order
- Representative is the set member in the head of the list

## Example Operation



## Time complexity

- Within this linked list representation, the make_Set and find_set operations are easy and take O(1) time
  - To carry out ***make_set(x),\*** we create a new linked list whose only object is x
  - To carry out f**ind_set(x)**, we just follow the pointer from x back to its set object and then return the member in the object the head points to
- The simplest implementation of the ***UNION\*** operation using the linked-list set representation takes significantly more time than MAKE-SET or FIND-SET.
- Since we must update the pointer to the set object for each object originally on y’s list, which takes time linear in the length of y’s list.
- While finding union, we append a list to the other and then have to update ALL elements of one of the two linked list sets.
- If we instead, maintain the length of each set and always ensure we update the pointers only for the smaller set, then we can reduce the time complexity again.

As the following theorem shows, however, a sequence of m MAKE-SET, UNION, and FIND-SET operations, n of which are MAKE-SET operations, takes O.m C n lg n/ time.

# Disjoint Set Forests

Instead of sets, we use rooted trees

In a disjoint set forest,

- each member points only to its parent
- The root of each tree contains the representative and is its own parent



## Union by rank

- We decide the root node based on the fact that the representative of the bigger family of the two becomes the representative of the combined family. 

- This way, in case one of the two families are bigger, we just have to update the rank to lesser number of people(people in the other family are lesser in number)

## Page Compression

- To reduce the time taken to find root/representative, we link each member of the family to route parent to root/representative. That way we can find anyone in O(1) time

## Useful Links

https://github.com/mateuszniewiadomski/Algorithms/blob/master/DisjointUnionSets/DisjointUnionSets.java

https://github.com/JayakrishnaThota/Algorithms-and-Data-Structures/blob/master/8 Sets/UnionFind.java

https://github.com/HareeshwarKarthikeyan/Disjoint-Sets

https://github.com/GigaMatt/data-structures-references/tree/master/disjoint-set-forest

https://github.com/sebastian-claici/dsa/blob/master/java/src/data_structures/disjoint_set/DisjointSet.java

https://github.com/sam1902/UnionFindJava