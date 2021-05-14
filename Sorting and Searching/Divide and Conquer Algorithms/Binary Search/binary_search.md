# Binary Search

Time complexity of searching through the array one by one is O(n)

### Problem- Can we do it faster if we sort and then check like we do in a dictionary?

## Approach

1. Divide
2. Conquer
3. Combine

## Recursion Tree

- Every level performs simple operation of finding whether to go left or go right or middle→ O(1) operation
- Depth wise, ie. how the window changes can be represented  by 2^d = n  ⇒ n = log d
- Upper bound of Total time, of big O will be the product of these ⇒ O(n) = O(1) * O(log n) = O(log n)

