package misc;

import java.util.Arrays;

/**
 * Created by Josh Wein on 11/27/2016.
 * Problem Statement: Create methods for rotating matrices 90, 180, and 270 degrees
 */
public class MatrixRotation {

    public static void transpose(int[][] matrix) {
        System.out.println("Transposing array");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void swapRows(int[][] matrix) {
        System.out.println("Swapping Rows");
        // Swap rows on opposite ends of the matrix and then move the pointers towards the middle
        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }
    }

    public static void rotate90DegreesClockwise(int[][] matrix) {
        System.out.println("Rotating 90 degrees to the right");
        swapRows(matrix);
        transpose(matrix);
    }

    public static void rotate90DegreesCounterClockwise(int[][] matrix) {
        System.out.println("Rotating 90 degrees to the left");
        transpose(matrix);
        swapRows(matrix);
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length*3; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        for (int i = 0; i < array.length*3; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Transpose");
        int[][] sampleArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(sampleArray);
        transpose(sampleArray);
        printArray(sampleArray);
        System.out.println("\nTest 2: Swap Rows");
        sampleArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(sampleArray);
        swapRows(sampleArray);
        printArray(sampleArray);
        System.out.println("\nTest 3: 90 Deg Right");
        sampleArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(sampleArray);
        rotate90DegreesClockwise(sampleArray);
        printArray(sampleArray);
        System.out.println("\nTest 4: 90 Deg Left");
        sampleArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(sampleArray);
        rotate90DegreesCounterClockwise(sampleArray);
        printArray(sampleArray);
    }
}
