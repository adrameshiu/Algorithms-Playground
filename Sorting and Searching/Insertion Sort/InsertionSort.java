import java.util.Arrays;
import java.util.List;

public class InsertionSort {
    //good for sorting small number of elements
    static int[] sort(int A[]) {
        int key;

        for (int j=1;j<A.length;++j){
            //assert(sorted(A,0,j-1)); //loop invariance -> our assumption that the left section of the array before key is sorted both during 'initialization(first iteration)' and 'maintenance(before each iteration)
            //we make the jth element the dividing element and move A[j] into the sorted sequence
            key = A[j];
            int i = j-1; //our comparisons are done with the previous element
            while (i>=0 && A[i] > key){
                //assert(sorted(A,0,j)); //loop invariance -> our assumption that the left section of the array is sorted till j now since we are trying to find the correct position of j by only placing the key once we find the correct position->'maintenance'(during each iteration)
                //when we find that a element is less than the previous, to move it backwards, we need to swap with each of the previous elements to find the correct position
                    //here it is an important assumption that the array till the current key is sorted and we can use a loop invariance
                    A[i+1] = A[i];
                    i = i-1;

            }
            A[i+1] = key; //once our loop condition fails, we know where to place the key element in the array
        }
        return A;
    }

    static Boolean sorted(int A[], int begin, int end) {
        for (int i = begin; i != end; ++i) {
            if (i + 1 != end) {
                if (A[i] > A[i+1]) return false;
            }
        }
        return true;
    }
}
