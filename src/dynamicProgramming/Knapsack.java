package dynamicProgramming;

/**
 * Created by Josh Wein on 11/21/2016.
 * Problem Statement: During a robbery, a burglar finds much more loot than he had expected and has to decide what
 * to take. His bag (or “knapsack”) will hold a total weight of at most W pounds. There are n
 * items to pick from, of weight w1, . . . , wn and dollar value v1, . . . , vn. What’s the most valuable
 * combination of items he can fit into his bag?
 * Implementation based off of: https://people.eecs.berkeley.edu/~vazirani/algorithms/chap6.pdf
 */
public class Knapsack {

    //  K(0) = 0
    //  for w = 1 to W:
    //      K(w) = max{K(w − wi) + vi : wi ≤ w}
    //  return K(W)
    public static int getMaxValueWithRepetitions(int targetWeight, int[] weights, int[] origValues) {
        int[] values = new int[targetWeight + 1];
        values[0] = 0;
        for (int w = 1; w <= targetWeight; w++) {
            int max = 0;
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] <= w) {
                    int test = values[w - weights[i]] + origValues[i];
                    if (test >= max) {
                        max = test;
                    }
                }
            }
            values[w] = max;
        }
        return values[targetWeight];
    }

    //    Initialize all K(0, j) = 0 and all K(w, 0) = 0
    //    for j = 1 to n:
    //        for w = 1 to W:
    //            if wj > w: K(w, j) = K(w, j − 1)
    //            else: K(w, j) = max{K(w, j − 1), K(w − wj, j − 1) + vj}
    //     return K(W, n)
    public static int getMaxValueWithoutRepetitions(int targetWeight, int[] weights, int[] origValues) {
        int[][]K = new int[targetWeight + 1][origValues.length + 1];
        for (int j = 1; j <= origValues.length; j++) {
            for (int w = 1; w <= targetWeight; w++) {
                if (weights[j-1] <= w) {
                    int test = K[w - weights[j-1]][j-1] + origValues[j-1];
                    if (test >= K[w][j-1]) {
                        K[w][j] = test;
                    } else {
                        K[w][j] = K[w][j-1];
                    }
                } else {
                    K[w][j] = K[w][j - 1];
                }
            }
        }
        return K[targetWeight][origValues.length];
    }

    public static void main(String[] args) {
        int[] weights = {6, 3, 4, 2};
        int[] values = {30, 14, 16, 9};
        int targetWeight = 10;
        System.out.println(getMaxValueWithRepetitions(targetWeight, weights, values));
        System.out.println(getMaxValueWithoutRepetitions(targetWeight, weights, values));
    }
}
