package backtracking;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Josh Wein on 11/20/2016.
 * Problem Statement: A derangement is a permutation p of {1,…,n} such that no item is in its proper position, i.e. pi≠i for all 1≤i≤n. derangement Write an efficient backtracking program with pruning that constructs all the derangements of n items.}
 */
public class Derangements {
    public static void printDerangements(Object[] arr) {
        derange(arr, new LinkedList<>());
    }

    private static void derange(Object[] arr, LinkedList<Object> solVector) {
        if (solVector.size() == arr.length) {
            processSolution(solVector);
        } else {
            for (int i = 0; i < arr.length; i++) {
                // If i == solVector.size() then it would put it in the same position
                // EX: If i == 0 you can't add it to an empty list since that would be putting the first item in the first position
                if (!solVector.contains(arr[i]) && i != solVector.size()) {
                    solVector.add(arr[i]);
                    derange(arr, solVector);
                    solVector.removeLast();
                }
            }
        }
    }

    private static void processSolution(LinkedList solVector) {
        System.out.println(Arrays.toString(solVector.toArray()));
    }

    public static void main(String[] args) {
        Integer[] testIntegerArray = {1, 2, 3, 4};
        Character[] testCharacterArray = {'a', 'b', 'c', 'd'};
        printDerangements(testIntegerArray);
        printDerangements(testCharacterArray);
    }
}
