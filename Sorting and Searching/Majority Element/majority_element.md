# Majority Element

## Objective

 To find the element appearing the maximum number of times in a list in linear time

## Approach

- with quick select we can find the element at any index in a sorted array
- for an element to be a majority element, it should be present in more than half of the array
- so if we find the middle element(by using quick select), and then check if it is appearing enough times by scanning through the list, we can find the majority element(if any exist)

