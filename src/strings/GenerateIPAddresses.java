package strings;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Josh Wein on 11/26/2016.
 * Problem Statement: You are given a String containing at least 4 numbers that represented an IPv4 Address, but the separator data -
 * i.e. the dots that separate each Byte in a 4 Byte Ipv4 address, has been lost.
 * Write a method that takes in this String and returns an ArrayList of Strings containing all possible IPv4 Addresses
 * that can be generated from the given sequence of decimal integers.
 */
public class GenerateIPAddresses {

    final static int STACK_SIZE = 3;

    public static ArrayList<String> generateIPAddresses(String input) {
        ArrayList<String> output = new ArrayList<>();
        Stack<IpLevelNode> stack = new Stack<>();

        // Testing all possibilities: size of 1 for the 1st slot, size of 2 for the 1st slot, or size of 3 for the 1st slot.
        for (int i = 1; i <= STACK_SIZE; i++) {
            stack.push(new IpLevelNode(0, input.substring(0, i), "", input.substring(i)));
        }

        IpLevelNode cur;
        int curLevel, permCounter;
        String pre, suc;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            curLevel = cur.level;
            pre = cur.predecessor;
            suc = cur.successor;
            // Found a valid solution
            if (curLevel == 3 && suc.length() == 0) {
                output.add(cur.predecessor);
                continue;
            }
            // Try all permutations attaching strings of size 1 - 3 from the successor string to the predecessor string
            permCounter = 1;
            while (permCounter <= STACK_SIZE) {
                if (suc.length() < permCounter) {
                    break;
                }
                String ipToAppend = suc.substring(0, permCounter);
                String successor = suc.substring(permCounter);
                if (ipToAppend.length() > 0) {
                    // Check if the number we want to place is a valid number in an ip address ie: 0 - 255
                    int numIpToAppend = Integer.parseInt(ipToAppend);
                    if (numIpToAppend <= 255) {
                        stack.push(new IpLevelNode(curLevel + 1, ipToAppend, pre, successor));
                    }
                }
                permCounter++;
            }
        }
        return output;
    }

    // This node stores current permutation information
    // If a node is made with a level greater than 0 that means it's getting a predecessor to connect to another section of an ip address
    static class IpLevelNode {
        int level = 0;
        String predecessor;
        String successor;

        public IpLevelNode(int level, String ipToAppend, String predecessor, String successor) {
            this.successor = successor;
            if (level == 0) {
                this.predecessor = ipToAppend;
            } else {
                this.level = level;
                this.predecessor = predecessor + "." + ipToAppend;
            }
        }
    }

    public static void main(String[] args) {
        System.out.printf("%-11s%s\n", "Input", "Output");
        System.out.printf("%-11s%s\n", "1001:", generateIPAddresses("1001"));
        System.out.printf("%-11s%s\n", "1010:", generateIPAddresses("1010"));
        System.out.printf("%-11s%s\n", "110110:", generateIPAddresses("110110"));
        System.out.printf("%-11s%s\n", "19216811:", generateIPAddresses("19216811"));
    }
}
