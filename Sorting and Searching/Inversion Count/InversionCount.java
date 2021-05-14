import java.util.Arrays;

public class InversionCount {

    static class OriginalArrayObject{
        int originalIndex;
        int originalElement;

        OriginalArrayObject(int index, int element){
            originalIndex = index;
            originalElement = element;
        }

        public void showElementObject(){
            System.out.print("Element = "+ originalElement + "  " + " original index = "+ originalIndex);
            System.out.println();
        }
    }

    static void merge(OriginalArrayObject[] objectA, int C[], int p, int q, int r) {
        OriginalArrayObject[] objectA1 = Arrays.copyOfRange(objectA,p,q);
        OriginalArrayObject[] objectA2 = Arrays.copyOfRange(objectA,q,r);

        int[] a1Sample = new int[objectA1.length];
        int[] a2Sample = new int[objectA2.length];

        int index = p;
        int i=0, j=0;

        //during merge, we compare left and right half and put the min element to the main array
        //this is on the assumption that left array and right array are individually sorted
        //for inversions, we see how far we are from getting the sorted array
        //so we need to see how many elements to right of the left array index are less than the current element at the left array index we are in
        //todo:while comparing A1[i] and A2[j], every time A1[i] is added, it would mean that it is greater than all elements till j in A2(while comparing and after exhausting A2 also)
        //todo:as we know left array is already sorted, this would mean every element after this element in the left array is also going to  be greater than the right array element. which means that many more inversions
        while (i < objectA1.length && j < objectA2.length) {
            if (objectA1[i].originalElement <= objectA2[j].originalElement) {
                C[objectA1[i].originalIndex] = C[objectA1[i].originalIndex] + j; // increment inversion counts for current index as it is greater than all elements in A2 till j

                objectA[index] = objectA1[i];
                i = i + 1;
            } else if (objectA1[i].originalElement > objectA2[j].originalElement) {
                objectA[index] = objectA2[j];
                j = j + 1;
            }
            index = index +1;
        }

        //adding remaining elements of left array(1st)
        while (i < objectA1.length) {
            C[objectA1[i].originalIndex] = C[objectA1[i].originalIndex] + j;

            objectA[index] = objectA1[i];
            i= i + 1;
            index = index + 1;
        }

        //adding remaining elements of right array(2nd)
        while (j < objectA2.length) {
            objectA[index] = objectA2[j];
            j= j + 1;
            index = index + 1;
        }
    }

    static void sort(OriginalArrayObject objectA[],int C[],int p,int r) {
        int q=0; //q is the middle
        if (r - p > 1) {
            q = (p + r) / 2;
            sort(objectA,C,p,q);
            sort(objectA,C,q,r);
            merge(objectA,C,p,q,r);
        }
    }

    public static int[] count(int[] A) {
        int[] C = new int[A.length];
        OriginalArrayObject[] objectA = new OriginalArrayObject[A.length];
        for (int i = 0; i < A.length; i++) {
            objectA[i] = new OriginalArrayObject(i, A[i]);
        }
        sort(objectA,C,0, objectA.length);
        return  C;
    }
}
