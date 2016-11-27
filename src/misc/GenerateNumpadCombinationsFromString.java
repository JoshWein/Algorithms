package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Josh Wein on 11/26/2016.
 * Problem Statement: Given a String that represents the digits pressed on a classic cell phone keypad -
 * return all possible letter combinations that the numbers could represent in an ArrayList of Strings.
 */
public class GenerateNumpadCombinationsFromString {

    public static  ArrayList<String> getStringsFromNums(String digits) {
        HashMap<Character, String> digitToStringMap = initMap();
        ArrayList<String> output = new ArrayList<>();
        Stack<PhoneNode> stack = new Stack<>();
        int length = digits.length();

        for (Character c : digitToStringMap.get(digits.charAt(0)).toCharArray()) {
            stack.push(new PhoneNode(String.valueOf(c), 1));
        }

        while(!stack.isEmpty()) {
            PhoneNode cur = stack.pop();
            if (cur.digCount == length) {
                output.add(cur.word);
            } else {
                for (Character c : digitToStringMap.get(digits.charAt(cur.digCount)).toCharArray()) {
                    stack.push(new PhoneNode(cur.word + c, cur.digCount + 1));
                }
            }
        }
        return output;
    }

    static class PhoneNode {
        String word;
        int digCount;

        PhoneNode(String word, int digCount) {
            this.word = word;
            this.digCount = digCount;
        }
    }

    public static HashMap<Character, String> initMap() {
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getStringsFromNums("222"));
    }
}
