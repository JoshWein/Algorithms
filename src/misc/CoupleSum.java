package misc;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Josh Wein on 11/27/2016.
 * Problem Statement: Given an array of integers, find two numbers such that they sum up to a specific target.
 * The method coupleSum should return the indices of the two numbers in the array, where index1 must be less than index2.
 */
public class CoupleSum {
    // Returns two numbers that add up to the target where n1's index < n2's index
    public static int[] coupleSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            if (map.containsKey(n)) {
                return new int[]{map.get(n), n};
            } else {
                // Store target - n so we can match it to a number later on
                map.put(target - n, n);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(coupleSum(new int[]{4, 2, 7, 1, 12}, 16)));
    }
}
