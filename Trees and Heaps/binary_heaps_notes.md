# Binomial Heaps

- proviedes efficient merge operation to combine two heaps

  

>  Combining 2 **BINARY HEAPS** takes O(n log n) time
>
> Combining 2 **BINOMAIL HEAPS** takes O(log n) time
>
> Amortized time to insert is O(1)



## Binomial Tree

- A binomial tree B(n) is a tree whose root has n children
- first child is B(n-1)
- second child is B(n-2)
- last child is B(0)

```
    B(0)   B(1)   B(2)    B(3)
     o      o     o       o---\
            |     |\      | \  \
            B0    B1 B0   B2 B1 B0
            
depth
0    o 1   o 1   o   1   o_____   1
|     |\      |  \  \
1          o 1   o o 2   o   o  o 3
|       |\  |
2                o   1   o o o    3
|
3                        o        1
```

> Each binomial tree B(n) can be formed by **taking two trees of B(n-1)**
> and putting **one of them as a child of the other's root.**

### Properties of Binomial Tree

> - height of a binomial tree is **n**
> - a binomial tree has **2^ n nodes**
> - **height= log nodes**(balanced)



## Binomial heap

- A *binomial heap* B(n) is a binomial tree that also has the max-heap property.

> A *binomial forest* is a list of binomial trees.

### Implementing a priority queue with a forest of binomial heaps

- forest to basically allow for number of nodes that are not powers of 2
- store forest in **order of increasing height**
- two trees **should not be of the same height**
- will contain at most log n trees(analogues to number of bits in binary)

### Operations on binomial trees

#### Link Operation

- applies to trees of same height
- makes one tree the first child of the other
- pick one with the larger key to be the top to maintain max heap property

> Time complexity : O(n)

#### Insert Tree Operation

- inserts a tree into a forest
- we have to link trees of the same height first and recursively insert the combined tree till there are no clashes

> Time Complexity: 
>
> =**O(t)** where t is the **number of trees** or
>
> = **O(log n)** where n is the **number of Elements**

##### Amortized time complexity of tree insertion

- similar to incrementing a binary counter
- ensure **each tree has 1 credit** attached
- **charge 2 for insertion** of tree
  - 1 unit of charge  to carry out insertion
  - 1 unit of charge to **place 1credit on tree**
- if there is already a tree of same height in the forest
  - link it with the tree to be inserted
    - link is O(n) time complexity->consume 1 credit from the tree already present and transfer the credit from other tree to the result of the link
- if there is no tree of same height
  - use 1 unit of credit to update the height of the forest 

## Merge Operation

- to **combine two binomial forests(**both have to be ordered by increasing height) and get a single forest

  ```
  Example
  merge {B0,B2} with {B1,B4} = {B0,B1,B2,B4}
  		
  merge {B0, B1} with {B1, B4} = {B0, B2, B4}
  
  ```

  > Time Complexity -> **O(t)** where t is the number of trees in both forests
  >
  > If there are n elements, t=log n =>> O(t)=**O(log n)**

## Extract max operation

- find the tree with the largest key(*take O(t)=O(log n) time*)
- remove  that tree and merge all its children into the forest->  *O(log n)*
  - We will have to reverse its children so that they are ordered by increasing height(O(log n))

```
Example
{B0, B2, B3, B4}

  suppose B2 has the largest key
  Extract and remove B2

 merge {B0, B3, B4}  {B0,B1} (children of B2)
    B0+B0 = B1
	B1+B1 = B2
	{B2, B3, B4}

```

> Time Complexity -> O(log n) + O(log n) + O(log n) -> **O(log n)**

