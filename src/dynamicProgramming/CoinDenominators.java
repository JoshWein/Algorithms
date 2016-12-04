package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Josh Wein on 11/20/2016.
 * Problem Statement: Given a set of coin denominators, find the minimum number of coins to make a certain amount of change.
 */
public class CoinDenominators {
    public static void main(String[] args) {
        int change = 20;
        int[] coinDenominations = {1, 6, 10};
        System.out.println(calculateMinCoinsNeeded(change, coinDenominations));
        ArrayList<Integer> denoms = new ArrayList<>();
        for (int x : coinDenominations) denoms.add(x);
        int possibilites = 0;
        System.out.println("All ways to make " + change + " out of " + denoms.toString());
        calculateAllWaysToMakeChange(change, denoms, new ArrayList<>(), possibilites);
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

    private static int calculateAllWaysToMakeChange(int totalChangeNeeded, ArrayList<Integer> coinDenominations, ArrayList<Integer> partial, int possibilities) {
        int s = 0;
        for (int x : partial) {
            s += x;
        }
        if (s == totalChangeNeeded) {
            possibilities++;
            System.out.println(possibilities + ": " + Arrays.toString(partial.toArray()));
        }
        if (s >= totalChangeNeeded) {
            return possibilities;
        }
        for (int i = 0; i < coinDenominations.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            for (int j = i; j < coinDenominations.size(); j++) {
                remaining.add(coinDenominations.get(j));
            }
            ArrayList<Integer> curPartial = new ArrayList<>(partial);
            curPartial.add(coinDenominations.get(i));
            possibilities = calculateAllWaysToMakeChange(totalChangeNeeded, remaining, curPartial, possibilities);
        }
        return possibilities;
    }
}
