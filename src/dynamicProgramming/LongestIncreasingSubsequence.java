package dynamicProgramming;

/**
 * Created by Josh Wein on 11/21/2016.
 * Implementation based off of: https://people.eecs.berkeley.edu/~vazirani/algorithms/chap6.pdf
 */
public class LongestIncreasingSubsequence {

    private static void findLongestIncreasingSubsequence(int[] sequence) {
        // Create our dp storage
        int[] lengthStorage = new int[sequence.length];
        int[] parentStorage = new int[sequence.length];
        lengthStorage[0] = 1;
        parentStorage[0] = - 1;
        int end = 0;
        int max = 1;
        // Go through each value
        for (int i = 1; i < sequence.length; i++) {
            lengthStorage[i] = 1;
            // Check it's predecessors for an existing path
            for (int j = i - 1; j >= 0; j--) {
                if (sequence[j] < sequence[i] && lengthStorage[j] + 1 > lengthStorage[i]) {
                    // L(j) = 1 + max{L(i) : (i, j) ∈ E}
                    lengthStorage[i] = lengthStorage[j] + 1;
                    parentStorage[i] = j;
                }
            }
            if (lengthStorage[i] > max) {
                max = lengthStorage[i];
                end = i;
            }
        }
        processSolution(sequence, parentStorage, end, max);
    }

    private static void processSolution(int[] sequence, int[] parentStorage, int end, int length) {
        System.out.print("Path: ");
        reconstructPath(sequence, parentStorage, end);
        System.out.println();
        System.out.println("Length: " + length);
    }

    private static void reconstructPath(int[] sequence, int[] parentStorage, int cur) {
        if (parentStorage[cur] == -1) {
            return;
        }
        reconstructPath(sequence, parentStorage, parentStorage[cur]);
        System.out.print(sequence[cur] + " ");
        return;
    }

    public static void main(String[] args) {
        int[] sequence = {5, 2, 8, 6, 3, 6, 9, 7};
        findLongestIncreasingSubsequence(sequence);
    }
}
