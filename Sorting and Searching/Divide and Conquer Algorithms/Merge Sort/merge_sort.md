# Merge Sort

## Approach

- cut the array in half
- recursively apply merge sort to each half
- take the two halfs and merge them into bigger array
- merge has 2 sequences and we need to produce,..1st 2 elements and put smaller one and increment



**Correctness of Merge Sort**

- No loop invariance as we arent using loops
- Pre condition and post conditions need to be true
- Post condition we need for merge sort is that the array should be sorted
- But for pre condition for merge sort, we dont know anything
- So we just need to check if the part of merge sort is sorted after merge sort is done and post condition of the entire merge sort is that the two sub parts of the sort calls are sorted

### Time Complexity

The complexity to merge mainly depends on the combine step which has a linear complexity of length of sub array size

So total time complexity is O(n) * O (log n) = O (n log n)

------

### Closest Pair Algorithm of numbers along 2 axes

Divide and sort set → time complexity of O(n log n)

While dividing, each level will have O(n log n)

The depth(number of levels) will be log n (we are dividing by 2 each time)

Thus total complexity = n log n * log n

**Return** → If we return copy of array, then O(n). But in java we return reference/address. Therefore, O(1)

------

# Karatsuba's Algorithm

- Polynomial addition and subtraction is fast. but multiplication is slow
- So, we need to think about reducing the polynomial multiplications by converting them to reuse the multiplied values that we have and converting them into additions/subtractions
- We subdivide the larger polynomial into 2 smaller halves(half the degree). First half multiplied by x^something as the first polynomial. and the 2nd half as the 2nd polynomial and express in terms of them
- Conquer step will do the recursions. Every recursion will divide the degree in half
- Combine steps just returns the out polynomial

### Runtime Analysis

3 recursive calls and each recursive multiply calls, degree is cut in half. plus suppress the add and sub complexity(captures the divide and combine part) to O(n)

Recurrence → T(n) = 3 * T(n/2) + O(n) (the reduction can also be inferred from how the time complexity is the same as the number of levels depth)

So in time complexity tree, each level will have 3 branches each taking a problem size of (n/2)

Total work is in the form of a geometric series