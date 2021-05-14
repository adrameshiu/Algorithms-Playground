import java.util.Arrays;
import java.util.LinkedList;

public class SeamCarving {   
    static int[] carve_seam(int disruption[][]) {
        //we need a cumulative matrix of disruption matrix(based on ADJACENT costs) as
        // we could have the least cost spread out in different corners of the matrix
        //and having min cost in top row doesnt necessarily mean min cost overall
        //cant be greedy about this as the total cost in bottom row is affected by all before it

        int disruptionRowCount = disruption.length;
        int disruptionColumnCount = disruption[0].length;

        int[][] cumulativeDisruptionMatrix = new int[disruptionRowCount][disruptionColumnCount];

        int previousDisruptionRightDiagonal;
        int previousDisruptionUpper;
        int previousDisruptionLeftDiagonal;

        //the first row of the cumulative matrix will be same as disruption matrix
        for (int j=0;j<disruptionColumnCount;j++) {
            cumulativeDisruptionMatrix[0][j] = disruption[0][j];
        }

        for (int i=1; i<disruptionRowCount; i++){
            for (int j=0; j<disruptionColumnCount; j++) {
                previousDisruptionLeftDiagonal = ((j-1) >= 0) ? cumulativeDisruptionMatrix[i-1][j-1] : Integer.MAX_VALUE;
                previousDisruptionUpper = cumulativeDisruptionMatrix[i-1][j];
                previousDisruptionRightDiagonal = (j+1) < disruptionColumnCount ? cumulativeDisruptionMatrix[i-1][j+1] : Integer.MAX_VALUE;

                cumulativeDisruptionMatrix[i][j] =  disruption[i][j] +
                        Math.min(Math.min(previousDisruptionLeftDiagonal, previousDisruptionUpper),
                            previousDisruptionRightDiagonal);
            }
        }

        LinkedList<Integer> seamPathLinkedList = findSeamPath(cumulativeDisruptionMatrix);

        Integer[] stitchedSeamList = seamPathLinkedList.toArray(new Integer[disruptionRowCount]);
        int[] stitchedSeamFromTop = new int[disruptionRowCount];

        int k=0;
        while (k<stitchedSeamList.length) {
            stitchedSeamFromTop[k] = stitchedSeamList[disruptionRowCount-1-k];
            k = k+1;
        }
        return stitchedSeamFromTop;
    }

    static LinkedList<Integer> findSeamPath(int cumulativeMatrix[][]) {
        //we need a cumulative matrix of disruption matrix(based on ADJACENT costs) as
        // we could have the least cost spread out in different corners of the matrix
        int rowLength = cumulativeMatrix.length;
        int columnLength = cumulativeMatrix[0].length;

        int leastCumulativeDisruption=0;
        //find the least value in the last row
        for (int j=0;j<columnLength;j++){
            int disruptionCost = cumulativeMatrix[columnLength-1][j];
            int costAtSeamStart = cumulativeMatrix[columnLength-1][leastCumulativeDisruption];
            if(disruptionCost < costAtSeamStart) {
                leastCumulativeDisruption=j;
            }
        }

        int i=(rowLength-1) - 1;//row before previous row
        int currentSeamIndex = leastCumulativeDisruption;
        int seamCostRight;
        int seamCostMiddle;
        int seamCostLeft;
        LinkedList<Integer> seamStitched = new LinkedList<Integer>();
        seamStitched.add(currentSeamIndex);
		
        while (i>=0) {
            seamCostLeft = (currentSeamIndex-1) >= 0 ?  cumulativeMatrix[i][currentSeamIndex-1] : Integer.MAX_VALUE;
            seamCostRight = (currentSeamIndex+1) < columnLength ? cumulativeMatrix[i][currentSeamIndex+1] : Integer.MAX_VALUE;
            seamCostMiddle = cumulativeMatrix[i][currentSeamIndex];

            int minDisruptionCost = 0;
            int seamIndex;
            if ((seamCostLeft < seamCostRight) && (seamCostLeft < seamCostMiddle)) {
                minDisruptionCost = seamCostLeft;
                currentSeamIndex = currentSeamIndex-1;
                seamStitched.add(currentSeamIndex);
            }
            else  if ((seamCostMiddle < seamCostLeft) && (seamCostMiddle < seamCostRight)) {
                minDisruptionCost = seamCostMiddle;
                seamStitched.add(currentSeamIndex);
            }
            else if ((seamCostRight < seamCostMiddle) && (seamCostRight < seamCostLeft)) {
                minDisruptionCost = seamCostRight;
                currentSeamIndex = currentSeamIndex+1;
                seamStitched.add(currentSeamIndex);
            } else { //for equal values
                if ((seamCostLeft == seamCostRight)) {
                    minDisruptionCost = seamCostLeft;
                    currentSeamIndex = currentSeamIndex-1;
                    seamStitched.add(currentSeamIndex);
                }
                else  if ((seamCostMiddle == seamCostLeft)) {
                    minDisruptionCost = seamCostMiddle;
                    seamStitched.add(currentSeamIndex);

                }
                else if ((seamCostRight == seamCostMiddle)) {
                    minDisruptionCost = seamCostRight;
                    currentSeamIndex = currentSeamIndex+1;
                    seamStitched.add(currentSeamIndex);
                }
            }
			i= i-1;
        }
        return seamStitched;
    }
}
