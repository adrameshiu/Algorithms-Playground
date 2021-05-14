
import java.lang.reflect.Array;
import java.util.Arrays;

public class MakingChange {
    static class MinChange {
        int coinsCount;
        int change;
        MinChange subChange;

        MinChange(int cnt, int money, MinChange existingChange) { 
            coinsCount = cnt;
            change = money;
            subChange = existingChange;
        }
    }

    public static int minimumCoins(int money, int[] coins) {
        MinChange[] memo = new MinChange[money+1];
        // Solve the leaf problem
        memo[0] = new MinChange(0, 0, null);
        // Solve the rest of the problems, bottom-up
        for (int currentAmount = 1; currentAmount <= money; ++currentAmount) {    //loop over total amount required
            MinChange leastChangeYet = null;
            for (int i = 0; i < coins.length; ++i) { // iterating over possible coin choices
                if ((currentAmount- coins[i]) >= 0) {
                    if (memo[currentAmount - coins[i]] != null) {
                    MinChange subChange = memo[currentAmount - coins[i]];

                    if (currentAmount == coins[i] + subChange.change) {
                        int coinsCount = 1 + subChange.coinsCount;
                        if (leastChangeYet == null || leastChangeYet.coinsCount > coinsCount) {
                            leastChangeYet = new MinChange(coinsCount, currentAmount, subChange);
                        }
                    }
                    }
                }
            }
            memo[currentAmount] = leastChangeYet;
        }

        int minNumberOfCoins = memo[money] != null ? memo[money].coinsCount : -1;
        return minNumberOfCoins;
    }
}
