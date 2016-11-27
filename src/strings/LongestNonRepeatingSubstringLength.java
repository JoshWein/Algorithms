package strings;

import java.util.HashSet;

/**
 * Created by Josh Wein on 11/27/2016.
 * Problem Statement: Given a String input, find the length of the longest substring that is made up of non-repeating characters.
 */
public class LongestNonRepeatingSubstringLength {

    public static int longestNRSubstringLen(String input) {
        if (input == null) {
            return 0;
        }

        char[] inputChars = input.toCharArray();
        int prev = 0;
        // Can only contain one letter at a time and we don't need to track anything else.
        HashSet<Character> charSet = new HashSet<>();
        for (char ch : inputChars) {
            if (!charSet.contains(ch)) {
                charSet.add(ch);
            } else {
                prev = Math.max(prev, charSet.size());
                charSet.clear();
                // Need to add back the current letter
                charSet.add(ch);
            }
        }
        return Math.max(prev, charSet.size());
    }

    public static void main(String[] args) {
        System.out.println(longestNRSubstringLen(null));
        System.out.println(longestNRSubstringLen(""));
        System.out.println(longestNRSubstringLen("a"));
        System.out.println(longestNRSubstringLen("aaa"));
        System.out.println(longestNRSubstringLen("abaca"));
        System.out.println(longestNRSubstringLen("abbababbcdasfaa"));
    }
}
