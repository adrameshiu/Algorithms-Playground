# Dynamic Programming in Graphs

## Motivation

- `Optimal Substructure` -> we try to narrow the optimal solution to a narrow set of candidates on top of which we can apply some brute force to get the best optimal
- identify redundancy so that we do not have to perform things repeatedly
- this is similar to a `recursive` approach..but it is usually a very bad idea to recurse on the whole thing..would take exponential time
- but we can optimize the amount we recurse on each time so that we recurse on a smaller set each time
  - we do this in a `divide and conqueur apporach` like merge sort, binary search etc where we only process half the set each time
  - `storing/memozing/cache` known answers and utilizing it again and again
- we need to ensure we don't have too many subproblems
- we also need to ensure we have subproblems which are smaller, not getting larger work-> that smaller sub problem helps us infer 

## Problems

- max weight independent set problem(divide into subproblems where we compare only previous element and 2nd previous element + current weight)
- Knapsack problem -> `dealing with budget and resource available` (divide into n-1 sets with  a budget of W-w if we pick the first one of weight w) 
- Sequence `Alignment` 

- Optimal search trees-> finding tree that minimizes the search time given frequency of key that is searched
  - like for spell checker

## How do we think of the optimal sub structure?

We look at the last element and see if it is part of the solution or not based on a condition

Look for questions that you ask yourself that is limiting -> for example, in optimal search trees, `if only i knew the root`, i could easily make the search tree i want-> optimal search tree

Look at the degrees of freedom we are trying to optimize to define the variable in question-> i and  j in above case(start and end of interval)