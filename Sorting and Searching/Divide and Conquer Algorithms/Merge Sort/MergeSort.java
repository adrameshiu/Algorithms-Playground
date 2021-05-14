
import java.util.Arrays;

public class MergeSort {

    static void merge(int A[], int p, int q, int r) {
        int[] A1 = Arrays.copyOfRange(A,p,q);
        int[] A2 = Arrays.copyOfRange(A,q,r);
        int[] ATemp = new int[A.length];

        int index = p;
        int i=0, j=0;

        // while both arrays have elements left, compare 1st element of each, move min between them to required array -> then compare the next element of the chosen array and the same 1st element and so on
        // this goes on till both have elements left
        // after that. add remaining elements from both arrays one after the other
        while (i < A1.length && j < A2.length) {
            if (A1[i] <= A2[j]) {
                A[index] = A1[i];
                i = i + 1;
            } else if (A1[i] > A2[j]) {
                A[index] = A2[j];
                j = j + 1;
            }
            index = index +1;
        }


        //adding remaining elements of left array(1st)
        while (i < A1.length) {
            A[index] = A1[i];
            i= i + 1;
            index = index + 1;
        }


        //adding remaining elements of right array(2nd)
        while (j < A2.length) {
            A[index] = A2[j];
            j= j + 1;
            index = index + 1;
        }
    }

    static void sort(int A[],int p,int r) {
        int q=0; //q is the middle
        if (r - p > 1) {
            q = (p + r) / 2;
            sort(A,p,q);
            //assert(sorted(A,p,q));

            sort(A,q,r);
            //assert(sorted(A,q,r));

            merge(A,p,q,r);
            //assert(sorted(A,p,r));

        }
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
