import java.util.Arrays;

public class MajorityElement {

    public static int majority(int[] A) {
        int midElement = select(A,0,A.length-1,A.length/2);
        int midCount = 0;
        for(int i=0; i<A.length;i++){
            if(A[i] == midElement) {
                midCount = midCount + 1;
            }
        }
        if(midCount > A.length/2) {
            return midElement;
        } else {
            return -1;
        }
    }

    static int select(int[] A, int lo, int hi , int k) {
        if(lo == hi)
            return A[hi];
        int pivot = partition(A,lo,hi);

        if(k == pivot)
            return A[pivot];
        else if(k < pivot)
            return select(A, lo , pivot - 1 , k); //till pivot index
        else
            return  select(A, pivot + 1, hi , k ); //after pivot index
    }
    static int partition(int A[], int lo, int hi) {
        int medianOfMedians = getMedianOfMedians(A,lo,hi);
        int mediansOfMediansIndex;

        for(mediansOfMediansIndex = 0; mediansOfMediansIndex < A.length; mediansOfMediansIndex++){
            if (A[mediansOfMediansIndex] == medianOfMedians) {
                break;
            }
        }
        swapElement(A, mediansOfMediansIndex, hi);
        int i = lo - 1;
        for(int j = lo ; j < hi; j++) {
            if (A[j] < medianOfMedians)
            {
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[hi];
        A[hi] = temp;
        return i+1;
    }

    static void swapElement(int A[], int index1, int index2 )
    {
        int temp = A[index2];
        A[index2] = A[index1];
        A[index1] = temp;
    }
    
    static int findMedian(int[] arr, int start, int end){
        Arrays.sort(arr, start, end);
        return arr[(start + end - 1)/2];
    }

    static int getMedianOfMedians(int[] arr, int start, int end){
        int n = end - start + 1;
        int [] median = new int[(n+4)/5];
        int counter;
        for(counter = 0; counter < n/5; counter++){
            median[counter] = findMedian(arr, start + counter * 5, (start + counter * 5)+5);
        }
        if (counter * 5 < n) {
            median[counter] = findMedian(arr, start + counter * 5, (start + counter * 5) + (n % 5));
            counter++;
        }
        if (counter == 1){
            return median[counter-1];
        }
        else
            return getMedianOfMedians(median, 0, median.length-1);
    }

}
