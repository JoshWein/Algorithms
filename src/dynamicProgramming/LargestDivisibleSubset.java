package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Josh Wein on 11/24/2016.
 * Problem Statement: Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 */
public class LargestDivisibleSubset {
    public static List<Integer> findLargestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        // Init dp arrays
        int[] dp = new int[nums.length];
        int[] parent = new int[nums.length];

        int max = 0;
        int start = 0;
        // Start at the end of the array and work towards the beginning
        for (int i = nums.length - 1; i >= 0; i--) {
            // for every other value
            for (int j = i; j < nums.length; j++) {
                // test for divisibility
                if (nums[j] % nums[i] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                start = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            result.add(nums[start]);
            start = parent[start];
        }
        return result;

    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3};
        System.out.println(findLargestDivisibleSubset(test1));
        int[] test2 = {1, 2, 4, 8};
        System.out.println(findLargestDivisibleSubset(test2));
    }
}
