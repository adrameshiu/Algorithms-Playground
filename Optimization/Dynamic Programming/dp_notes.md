# Dynamic Programming

- solving sub problems in a bottom up fashion

## When to do dynamic programming?

- when we have optimal substructure
- And overlapping sub problems
- total number of problems should be small or manageable since we'll be allocating space to store results
- iterating over processes and finding best one



## DNA Sequence Alignment

> Time complexity -> O(mn)
> Space complexity(for 2D memo toble) -> O(mn)

- strand of DNA is a string of molecules called bases(A,G,C or T)
- We want to compare the DNA of different organisms and see how well they align
- We try to line them up and see how many matching characters are possible
- Since both sequences need not be of equal length, we can insert **gaps**(represented as underscores) in either sequences to help them line up
- We define the similarity with a score of best alignment 

### Looking at this problem as a Edit Distance Problem

- we look at this in a perspective of seeing how many edits are required to turn the first string to the other(so that they match)
  - edits could be one of the three options
    - change character (when there is a mismatch)
    - insert a character(corresponds to gap in the first string)
    - delete a character(corresponds to gap in the second string)

#### Subproblem identification

Instead of using the entire X and Y as the inputs to recursive function, we use indices i and j to mark how much sub string of X and Y are we looking at. 



## Memoization(storing previous results for reuse)

- basically saving the results of the work
- we break a problem into sub problems → if the same sub problem comes in again, we just used the saved results from previous results
- We record each solution and then before updating a solution, just look it up (maybe like a table of some form) if it is available and then update from that
- We store the results in an array for reference
- We first check the table, if we have solution for subproblem, then we just return solution
- The other part of the algo is the same as the repeated recursive calls, except that when we return, we first save the cost to the result aray

> use a 2D table indexed by i and j for edit distance