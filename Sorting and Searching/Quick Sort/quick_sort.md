# Quick Sort

Similar to merge sort but technically opposite

All work is mainly done in divide

## Divide step

- Pick an element to be a pivot and partition the array such that all less than it is to the left and all the elements greater are to the right
- Choice of pivot is an important one and gives different outcomes. so quick sort is basically a family
- Lets choose last element as pivot
- But partitioning does not sort left or right side. It just compares and puts it across the pivot
- Elements in the range [0, pivot) are to the left()≤ to pivot
- And (pivot, length]
- Pivot is in its final resting place

Worst case time complexity is O(n^2)

- We use two fingers(i,j) of opposite hand for the pivot. but we do not cross our arms. left of first hand is less than,  middle of two hands are greater and right of 2nd hand are the elements that are still not processed
- Increment i and then always increment j no matter at the end of the condition
- If greater than pivot, then just increment j
- If you find a less than after this, swap that element with the 1st element between i and j and increment i and j
- Then in the end, swap the last element with the start of the part greater than pivot
- And then apply this recursively to the two halves
- Body of the loop are 2 constant time operations
- We increment j n times → which is the length of the array

One for loop→ index of j is just going to increase by 1 and i is going to increase by 1

so at most it will increase by O(2n) which is O(i)

## Time Complexity

T(n) = T(n-1) + n

Worst case scenario → theta(n^2) by continuously applying to the above equation in recurssion

Best Case Scenario → T(n) = 2 T(n/2) + O(n), by master method it is in theta(n * log n)

## Choosing pivots randomly

- If we keep choosing pivots from the end, prone to attacks and not optimal when most elements are already sorted. and also the time complexity is really bad as it will do a lot of random shuffling before it settles
- So we try to pick a random element from the middle and swap with the last element first.
- by choosing from the middle 80% will have a better probability to end with a lower time complexity



## Random Code Nodes

input is an array

do the randomized pivot

all work in divide step →

pick a pivot

partition the array according to pivot → left is lesser

in partition → half open region form [0,i)

[i+1,

CONQUER → recursively apply quick sort to left and right

Nothing to do in combine

invarient

[i+1,j) greater than region

[j, n-1) is not processed

algo is done when j is done

and swap with i at the end of the partition