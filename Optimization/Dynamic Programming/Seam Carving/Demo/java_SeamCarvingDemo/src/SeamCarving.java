import java.util.Arrays;
import java.util.LinkedList;

public class SeamCarving {
	    static class CumulativeSeamObject {
        CumulativeSeamObject(int val, LinkedList<Integer> connectedSeam) {value=val; linkedSeamPath=connectedSeam;}
        LinkedList<Integer> linkedSeamPath;
        int value;
    }
    static int[] carve_seam(int disruption[][]) {
        //we need a cumulative matrix of disruption matrix(based on ADJACENT costs) as
        // we could have the least cost spread out in different corners of the matrix
        //and having min cost in top row doesnt necessarily mean min cost overall
        //cant be greedy about this as the total cost in bottom row is affected by all before it

        int disruptionRowCount = disruption.length;
        int disruptionColumnCount = disruption[0].length;

        //creating a matrix of cumulativeSeamObjects as we need to use the connected path also to return output
        CumulativeSeamObject[][] cumulativeDisruptionMatrix = new CumulativeSeamObject[disruptionRowCount][disruptionColumnCount];

        CumulativeSeamObject previousDisruptionRightDiagonal;
        CumulativeSeamObject previousDisruptionUpper;
        CumulativeSeamObject previousDisruptionLeftDiagonal;

        //the first row of the cumulative matrix will be same as disruption matrix

        for (int j=0;j<disruptionColumnCount;j++) {
            LinkedList<Integer> connectedSeam = new LinkedList<>();
            connectedSeam.add(j);
            cumulativeDisruptionMatrix[0][j] = new CumulativeSeamObject(disruption[0][j],connectedSeam);
        }

        for (int i=1; i<disruptionRowCount; i++){ //start from 2nd row as we already wrote first row
            for (int j=0; j<disruptionColumnCount; j++) { //start from 1st column itself
                previousDisruptionLeftDiagonal = ((j-1) >= 0) ? cumulativeDisruptionMatrix[i-1][j-1] : new CumulativeSeamObject(Integer.MAX_VALUE,null);
                previousDisruptionUpper = cumulativeDisruptionMatrix[i-1][j];
                previousDisruptionRightDiagonal = (j+1 < disruptionColumnCount) ? cumulativeDisruptionMatrix[i-1][j+1] : new CumulativeSeamObject(Integer.MAX_VALUE,null);

                int minDisruptionCost = 0;
                int seamIndex=0;
                LinkedList<Integer> cumulativeConnectedSeam = new LinkedList<>();

                if ((previousDisruptionLeftDiagonal.value < previousDisruptionRightDiagonal.value) &&
                        (previousDisruptionLeftDiagonal.value < previousDisruptionUpper.value)) {

                    minDisruptionCost = previousDisruptionLeftDiagonal.value;
                    cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionLeftDiagonal.linkedSeamPath.clone(); //using clone because we just want the data in the linkedlist and dont want to map to it
                }
                else  if ((previousDisruptionUpper.value < previousDisruptionLeftDiagonal.value) &&
                        (previousDisruptionUpper.value < previousDisruptionRightDiagonal.value)) {
                    minDisruptionCost = previousDisruptionUpper.value;
                    cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionUpper.linkedSeamPath.clone();
                }
                else if ((previousDisruptionRightDiagonal.value < previousDisruptionUpper.value) &&
                        (previousDisruptionRightDiagonal.value < previousDisruptionLeftDiagonal.value)) {
                    minDisruptionCost = previousDisruptionRightDiagonal.value;
                    cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionRightDiagonal.linkedSeamPath.clone();
                } else {
                    //hack for equal to
                    if ((previousDisruptionLeftDiagonal.value <= previousDisruptionRightDiagonal.value) &&
                            (previousDisruptionLeftDiagonal.value <= previousDisruptionUpper.value)) {

                        minDisruptionCost = previousDisruptionLeftDiagonal.value;
                        cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionLeftDiagonal.linkedSeamPath.clone(); //using clone because we just want the data in the linkedlist and dont want to map to it
                    }
                    else  if ((previousDisruptionUpper.value <= previousDisruptionLeftDiagonal.value) &&
                            (previousDisruptionUpper.value <= previousDisruptionRightDiagonal.value)) {
                        minDisruptionCost = previousDisruptionUpper.value;
                        cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionUpper.linkedSeamPath.clone();
                    }
                    else if ((previousDisruptionRightDiagonal.value <= previousDisruptionUpper.value) &&
                            (previousDisruptionRightDiagonal.value <= previousDisruptionLeftDiagonal.value)) {
                        minDisruptionCost = previousDisruptionRightDiagonal.value;
                        cumulativeConnectedSeam = (LinkedList<Integer>) previousDisruptionRightDiagonal.linkedSeamPath.clone();
                    }
                }

                int cumulativeDisruptionValue = disruption[i][j] + minDisruptionCost;
                cumulativeConnectedSeam.add(j);
                cumulativeDisruptionMatrix[i][j] = new CumulativeSeamObject(cumulativeDisruptionValue,cumulativeConnectedSeam) ;
            }
        }

        //finding minimum cumulative disruption in last row
        int leastCumulativeDisruptionColumnIndex = 0;
        for (int j=0;j<disruptionRowCount;j++){
            CumulativeSeamObject currentCumulativeObject = cumulativeDisruptionMatrix[disruptionRowCount-1][j];
            CumulativeSeamObject LeastDisruptionObject = cumulativeDisruptionMatrix[disruptionRowCount-1][leastCumulativeDisruptionColumnIndex];
            if(currentCumulativeObject.value < LeastDisruptionObject.value) {
                leastCumulativeDisruptionColumnIndex=j;
            }
        }
        CumulativeSeamObject leastCumulativeDisruptionObject =  cumulativeDisruptionMatrix[disruptionRowCount-1][leastCumulativeDisruptionColumnIndex];

        //linkedlist containing best seam constructed yet for minimum cumulative disruption in last row
        //will contain the required path
        LinkedList<Integer> linkedSeam = leastCumulativeDisruptionObject.linkedSeamPath;

        //converting linked list to array
        int[] stitchedSeamPath = new int[linkedSeam.size()];
        for(int k =0; k< linkedSeam.size(); k++){
            stitchedSeamPath[k] = linkedSeam.get(k);
        }

        System.out.println(Arrays.toString(stitchedSeamPath));
        return stitchedSeamPath;
    }
	}
