package misc;

import java.math.BigInteger;

/**
 * Created by Josh Wein on 11/27/2016.
 * Problem Statement: Write a method that efficiently calculates arbitrarily large fibonacci numbers
 */
public class EfficientFibonacci {

    // Returns the nth fibonacci number in a BigInteger to prevent overflowing
    public static BigInteger fibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }
        BigInteger nMinusTwoValue = new BigInteger("0");
        BigInteger nMinusOneValue = new BigInteger("1");
        BigInteger current;
        for (int i = 2; i <= n; i++) {
            current = nMinusOneValue.add(nMinusTwoValue);
            nMinusTwoValue = nMinusOneValue;
            nMinusOneValue = current;
        }
        return nMinusOneValue;
    }

    public static void main(String[] args) {
        // Can easily calculate the first 100k fibonacci numbers
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ": " + fibonacci(i));
        }
        System.out.println("...");
        System.out.println("100000: " + fibonacci(100000));
    }
}
