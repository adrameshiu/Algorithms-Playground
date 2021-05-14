# Quick Select

## Objective

to find the difficulty of the 2nd assignment

get feedback about the assignment from the kth lowest grade in the class

Aim → to find that kth lowest grade

## Insight

after partitioning the grades into 2 parts, kth lowest grade must be either

- the pivot itself
- or an element in the left sub array(less than pivot)
- element to the right sub array(greater than pivot)

Depending on value of k, we just need to explore one of the sub arrays

## Time Complexity(Quora)

We expect that the position of the random element in the sorted sequence will be uniformly distributed between 0 and N-1, where N is the length of the data set.

This means there's at least a 1/2 chance that it's between N/4 and 3N/4. That means that at least half the time, we get a pivot that reduces the problem to no more than 3/4 of the previous problem size.

Each pivot selection and filtering of the elements by the pivot takes O(N) if there are N elements remaining.

Based on the above, we expect the problem to be reduced to no more than 3/4 of its size after two of these O(N) passes.

We therefore get a recurrence relationship of

T(N) = T(3N/4) + 2 * O(N) = T(3N/4) + O(N),

the last step being justified by the fact that constants can be collapsed into the big-O.

Then,

The last step is justified because the geometric series sums to a constant and big-O collapses constants.

With bad  pivots, O(n^2) like when last element is pivot and we want first element

With good pivots O(n)

