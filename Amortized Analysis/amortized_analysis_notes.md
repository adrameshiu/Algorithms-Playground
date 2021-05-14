# Amortized Analysis

- Instead of focusing on the actual cost of a single operation, we compute the cost of a sequence of n operations and find the average to get a tighter bound.

## Dynamic Tables

- In java, we need to allocate space to any datastructure that uses contiguous memory(like arrays, stacks, hashtables)-> either internally or manually.
- Using a parameter that shows how full the table is(load factor), we move the contents of the table to a bigger table or smaller table.

> Load Factor , $\alpha(Table)$ = Table.number_of_elements / Table.size 

If the table.num becomes more than table.size(load factor more than 100%), we move it to a new table of double the size

> cost of one insertion will be of two variations
>
> ​	if there is space to insert -> O(1)
>
> ​	if there is no space, we need to copy EACH ELEMENT to a new table -> O(n)

## Accounting method applied to n table insertions

> Charge 3 units to each table insertion

Consider right after there has been an expansion to a table of size m

It'll be half full(m/2 elements) as the size of old list was doubled

- Over the next m/2 insert operations, of the **3 units we charge** per insertion, we 

  - use 1 unit to pay for insertion
  - place one credit on new element added
  - place one credit on any of the m/2 elements that still dont have a credit

  So next time when we move them to a bigger table, all m  elements have enough credits to pay to insert themselves into the bigger table 

## Insertions and Deletions

> Similar to insertions, we also shrink when go below a certain load factor
>
> But if we keep that threshold as going below 50%, then there will be a lot of fluctuations. 
>
> So we keep the threshold as 25%

### Potential Method applied to insertion and deletion

Defining the potential function as follows

```
P(T) = { 2 T.num - T.size    if α(T) >= 1/2
       { T.size/2 - T.num    if α(T) < 1/2
```

- When load factor is half, then P(T) will be 2* (T.size/2) - T.size = 0
- As the load factor increases from 1/2 to 1, potential increases
- When the load factor reaches 1, P(T) = 2 * (T.num) - T.num= T.num
- There is enough potential to copy all elements
- 

## Potential Method review

- usually there is a cheap case and expensive case
- in cheap case, potential only changes by a bit 
- define potential function for expensive scenario

### Amortized Weight Balanced Weight Binary Search Tree

- trees should be balanced so that height is always logarithmic

- one trick is that we dont have to keep the tree fully balanced(almost balanced is good enough)

- every node record how many nodes are below it

- if diff between left and right subtree of x is no less than half the size of x, we say it is balanced

- so every insert we dont have to do much unless it crosses boundary

  ```
  |x.left.size - x.right.size| < 1/2 * x.size
  ```

  When we cross threshold, We pick the highest such x and rebuild tree

> Potential function chosen -> P(T) = 2 $\Sigma_x(|x.left.size-x.right.size|)$

 Consider an insert which makes the subtree unbalanced...we need to rebuild the tree then 

```
​```
|x.left.size - x.right.size| > 1/2 * x.size
​```

```

Actual Insert into this tree is O(log n)

Change in potential worst case is when we insert somewhere down the tree, but beacause of that each parent of it also gets adjusted -> which carries on till root-> log(n)



