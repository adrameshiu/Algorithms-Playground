# Heaps

## Motivations

- Want a data structure that returns maximum(or minimum) keyed element of the set->priority queue
- Want to do inplace sorting
- Want to be able to change the key associated with an element

## When do we use heaps?

When we see repeated min/max operations

## Applications

- selection sort-> repeated min operations-> we can use heap sort instead
- event manager/priority queue
- median maintenance
- speeding up djikstras

## Heap Array Representation

```
left(i) = 2i + 1

right(i) = 2(i + 1) = 2i + 2

parent(i) = ⌊(i - 1)/2⌋
```

## Heap Operations and time complexity

```
|   operation    | use                    |time comp. |
|----------------|:----------------------:|----------:|
| build_max heap | heap of UNORDERED array|  O(n)     |
| maximum        | max element in array   |  O(1)     |
| extract_max    | remove max element..   |  O(log(n))|
|				 | size reduces by 1      |           |
| sortInPlace    | sorts array inplace.   |O(n*log(n))|
| updatekey      | updateskey in heap..   |  O(log(n))|
| insert         | new element            |  O(log(n))|

```



## Converting an array to a max_heap

- Need to turn an array that is almost max heap(except for one element) into a max heap

- use `max_heapify` ->> moves element at position i to its correct position

- goes along the height only

- For this to work, ***all the sub trees to left and right children of key at position i are max heaps*** 

- We swap key at position i with larger of its two children and repeat till we reach a point when it is greater than all its children-> correct position

  

  >  Time complexity of max_heapify is **O(log n)**

## Build_max_heap

> Loop invarient of build_max_heap-> **all its sub trees are max heaps**

>  Time complexity of build_max_heap
>
> ​	(*vague*) is **O( n * log n)**
>
> ​	(tight upper bound) is **O(n)** -> time for max_heapify depends on height of node and it is called most times on leaf nodes/nodes with low height
>
> This is because we run max heapify on all elements from the last element(total n times)

### Algorithm

- Find last parent by size/2 -1
- apply max heapify from last parent to root



## extract_max method

- removing the max element
- since the max element is at the root of the tree, once we remove this element, we have to rearrange the tree again and make a max heap again since the root parent was removed. but since removing itself is too expensive
- we swap the root with the last element and reestablish max heap property for the new root node   and then remove the max element

```java
E extract_max() {
	E max = data.get(0);                    // O(1)
	data.set(0, data.get(data.size()-1));   // O(1)
	data.remove(data.size()-1);             // O(1)
	max_heapify(0, data.size());            // O(log(n))
	return max;
}
```

>  Time complexity of extract_max: O(log(n)) -> because of max heapify

## sortInPlace method

- Iteratively applying max heapify(moving element to right place) after swapping last element with root

> Loop invariant ->  the front part represents the
> heap and the back part represents the sorted output array

```java

static <E> void sortInPlace(ArrayList<E> A, 
							BiPredicate<E,E> lessThanOrEqual)
{
	Heap<E> H = new Heap<E>(lessThanOrEqual);
	H.data = A;
	H.build_max_heap();                                // O(n)
	for (int i  = H.data.size() - 1; i != 0; --i) {    // n iterations
		swap(H.data, 0, i);
		H.max_heapify(0, i);                           // O(log n)
	}
}
```

> Time complexity:
> The for loop executes n times, and max_heapify is O(log(n)),
> so we have O(n log(n))



## Update Key

- If key has increased, move the element updwards till it is greater than children

```
void increase_key(int i) {
    while (parent(i) >= 0 && lesseq.test(data.get(parent(i)), data.get(i))) {
        swap(data, i, parent(i));
        i = parent(i);
    }
}
```



> Time complexity: O(log(n))	



## Insert key

- inserting a new element into the last position and then call update/increase_key
- Will run to root max

> Time complexity: 
>
> O(log(n))    (amortized time complexity)