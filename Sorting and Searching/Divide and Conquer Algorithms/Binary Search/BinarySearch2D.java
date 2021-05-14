
public class BinarySearch2D {
    static int[] search(int[][] M, int q) {
    //q is the key we have to find and M is the 2D array

    int[] coOrds = {-1, -1};
		if(M.length == 0)
			return coOrds;

        int rowStart = 0;
        int rowEnd = M.length - 1;
        int rowMiddle = (rowStart + rowEnd)/2;

        int columnStart = 0;
        int columnEnd = M[0].length -1;

        int columnMiddle = (columnStart + columnEnd)/2;

        //first check across first row,
        //if the element is not within start and end index of the columns along this row,
        // then we need to go to next row

        while(rowStart <= rowEnd) {
            rowMiddle = (rowStart + rowEnd) / 2;

            if (M[rowMiddle][columnEnd] >= q && M[rowMiddle][0] <= q) {
                break; //element will be in this row
            } else if (M[rowMiddle][0] < q) {
                rowStart = rowMiddle + 1;
            } else if (M[rowMiddle][0] > q) {
                rowEnd = rowMiddle - 1;
            }
        }
        while(columnStart <= columnEnd) {
            columnMiddle = (columnStart + columnEnd)/2;

            if (M[rowMiddle][columnMiddle] == q) {
                coOrds[0] = rowMiddle;
                coOrds[1] = columnMiddle;
                return coOrds;
            } else if (M[rowMiddle][columnMiddle] < q) {
                columnStart = columnMiddle + 1;
            } else if (M[rowMiddle][columnMiddle] > q) {
                columnEnd = columnMiddle - 1;
            }
        }
         return coOrds;
    }
}
