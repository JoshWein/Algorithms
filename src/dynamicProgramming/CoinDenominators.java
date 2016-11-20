package dynamicProgramming;

/**
 * Created by Josh Wein on 11/20/2016.
 * Problem Statement: Given a set of coin denominators, find the minimum number of coins to make a certain amount of change.
 */
public class CoinDenominators {
    public static void main(String[] args) {
        int change = 50;
        int[] coinDenominations = {1, 5, 10, 25};
        System.out.println(calculateMinCoinsNeeded(change, coinDenominations));
    }

    private static int calculateMinCoinsNeeded(int totalChangeNeeded, int[] coinDenominations) {
        // Create an array to hold all possible values
        int[] minCoinsNeeded = new int[totalChangeNeeded + 1];
        for(int i = 1; i < minCoinsNeeded.length; i++) {
            minCoinsNeeded[i] = Integer.MAX_VALUE;
        }
        for (int currentSum = 1; currentSum <= totalChangeNeeded; currentSum++) {
            // Try each denomination
            for (int j = 0; j < coinDenominations.length; j++) {
                if (coinDenominations[j] <= currentSum && minCoinsNeeded[currentSum - coinDenominations[j]] + 1 < minCoinsNeeded[currentSum]) {
                    minCoinsNeeded[currentSum] = minCoinsNeeded[currentSum - coinDenominations[j]] + 1;
                }
            }
        }
        return minCoinsNeeded[minCoinsNeeded.length - 1];
    }
}
