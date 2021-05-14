
import java.util.Arrays;

public class HeapSort {

    static int heapifyIndex(int A[], int n, int i){
        //to ensure that the subtree rooted at i satisfies the heap property

        //for a node stored at index i,
        // the left child is stored at index 2i + 1
        // and the right child is stored at index 2(i + 1)


        int nextIndexInPath = i;
        int parentIndex = i;
        int leftChildIndex = 2*i + 1;
        int rightChildIndex = 2*(i+1);

        int parentElement = A[i];
        int leftChildElement = leftChildIndex < n ? A[leftChildIndex] : Integer.MIN_VALUE;
        int rightChildElement = rightChildIndex < n ? A[rightChildIndex] : Integer.MIN_VALUE;


        int maxElement = parentElement;
        if (rightChildIndex < n){ //for the case when there is a left child, but no right child
            if (parentElement < rightChildElement) {
                nextIndexInPath = rightChildIndex;
                maxElement = rightChildElement;
            }
        }

        if (maxElement <= leftChildElement) {
            nextIndexInPath = leftChildIndex;
        }
        swapElement(A, parentIndex, nextIndexInPath);
        return nextIndexInPath;
    }

    static void buildMaxHeap(int A[]){
        int last_parent = A.length/ 2 - 1; //leaf nodes do not have children
        for (int i = last_parent; i != -1; --i) {
            heapify(A,A.length, i);
        }
    }

    static void heapify(int A[], int n, int i) { //heapify elements from i to n
        int currentParent=i, nextParent = i; //trying to find the correct position of i
        while (currentParent < n) {
            nextParent = heapifyIndex(A, n, currentParent);
            if(currentParent != nextParent) //if, we havent found the correct position for it, we move along the path till we do
                currentParent = nextParent;
            else //this means we have found the correct position of i
                break;
        }
    }

    static void heapSort(int A[]){
        //build a max heap from an array elements
        //get root as max value...root node satisifies max heap property
        buildMaxHeap(A);

        //place the max element(root) at the last index and do heapify on the rest of the elements
        for (int i  = A.length - 1; i > -1; --i) {    // n iterations
            //loop invariant => after  each iteration, [0,i) elements will be a heap && [i,n) will be sorted from min to max

            swapElement(A, 0, i); //swapping the max element at root with last element that is before the section that just contains max roots that were extracted
            //we dont consider the max elements taken in this heapify section
            heapify(A, i, 0);   //heapify from element 0 to element at i->[0,i]  // O(log n)
        }
    }

    static void swapElement(int A[], int index1, int index2 ) {
        int temp = A[index2];
        A[index2] = A[index1];
        A[index1] = temp;
    }

}
