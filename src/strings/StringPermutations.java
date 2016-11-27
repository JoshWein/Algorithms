package strings;

import java.util.ArrayList;

/**
 * Created by Josh Wein on 11/26/2016.
 * Problem Statement: Recursively generate all permutations of a given String.
 */
public class StringPermutations {
    public static ArrayList<String> getPermutations(String s) {
        if (s == null) {
            return null;
        }
        ArrayList<String> perms = new ArrayList<String>();
        perm("", s, perms);
        return perms;
    }

    public static void perm(String pre, String post, ArrayList<String> perms) {
        if (post.length() == 0) {
            perms.add(pre);
        } else {
            for (int i = 0; i < post.length(); i++) {
                perm(pre + post.charAt(i), post.substring(0, i) + post.substring(i + 1), perms);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getPermutations("abd"));
    }
}
