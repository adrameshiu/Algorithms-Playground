import java.util.Arrays;

public class EditDistance {
    public static int editDistance(String word1, String word2) {
        int finalEditDistance;
        //m is the number of characters in word1 and n is the number of characters in word2
        int m = word1.length();
        int n = word2.length();
		
        //if we have empty string, return length of other string
        if(m==0 || n==0) {
            return m+n;
        }

        //lets create a distance matrix of size [m+1][n+1]...the +1 is because we first calculate for empty source and target strings too
        //each value in the matrix specifies the edit distance between  SUB-STRINGS of word1 and word2 of length(from [0,i) of word1 and [0,j) of word2)
        int[][] distanceMatrix = new int[n+1][m+1]; //word 2 along the rows as in each column has word2 chars

        distanceMatrix[0][0] = 0; //both substrings are empty-> distance is 0

        //we can transform the source string to empty string(when target is empty) by just
        //dropping all characters{deleting each character-> distance + 1}
        for (int i=1;i<n+1;i++) { //we start from 1 as we are manually filling for the other empty string(index 0 )
            distanceMatrix[i][0] = distanceMatrix[i-1][0] + 1; //drop operation -> distance+1
        }

        //when source is empty, we can transform it to any target string by just directing inserting characters
        //inserting all characters{inserting each character-> distance + 1}
        for (int j=1;j<m+1;j++) { //we start from 1 as we are manually filling for the other empty string(index 0 )
            distanceMatrix[0][j] = distanceMatrix[0][j-1] + 1; //drop operation -> distance+1
        }

        int diagonalCost;
        int previousDistanceToLeft;
        int previousDistanceUpper;
        int previousDistanceDiagonal;

        //filling the table from empty strings to full strings step by step ->  bottom up
        for (int j=1; j<m+1; j++){  //word 1 is along the column
            for (int i=1; i<n+1; i++) { //word 2 is along the rows
                previousDistanceToLeft = distanceMatrix[i-1][j];
                previousDistanceUpper = distanceMatrix[i][j-1];
                previousDistanceDiagonal = distanceMatrix[i-1][j-1];


                //we are checking index-1 as the first index is all reserved
                // for empty string comparisons only
                char currentWord1Char = word1.charAt(j-1);
                char currentWord2Char = word2.charAt(i-1);

                //diagonal condition -> if the current characters are same, then diagonalCost=0
                //otherwise 1
                if(currentWord1Char == currentWord2Char) {
                    diagonalCost = 0;
                } else {
                    diagonalCost = 1; //replace
                }

                //distance at current index will be min of the the previousDistanceToLeft+1,previousDistanceUpper+1, diagonalUpdistance+cost
                distanceMatrix[i][j] =  Math.min(
                        Math.min(previousDistanceToLeft+1, // deletion
                                previousDistanceUpper+1), // insertion
                        previousDistanceDiagonal+diagonalCost);
                }
        }
        finalEditDistance = distanceMatrix[n][m];
        return  finalEditDistance;
    }

    public static void printDistanceMatrix(String word1, String word2, int[][] matrix) {
        //System.out.print(Arrays.deepToString(matrix));
        char[] word1Array = word1.toCharArray();
        System.out.println();
        System.out.println("    " + Arrays.toString(word1Array));
        for (int j=0;j<=word2.length();j++) {
            char word2character = j>=1 ? word2.charAt(j-1) : ' ';
            System.out.println(word2character + Arrays.toString(matrix[j]));
        }
    }
}
