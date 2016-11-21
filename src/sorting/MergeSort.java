package sorting;

import java.util.Arrays;

/**
 * Created by Josh Wein on 11/17/2016.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] testArray = {5, 2, 7, 1, 3, 15, 4, 2, 17, 9};
        mergeSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    private static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int middle = arr.length/2;
        // Make two arrays and store each half of the original
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];
        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, middle, right, 0, right.length);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, arr);
    }

    private static void merge(int[] left, int[] right, int[] arr) {
        int leftPointer = 0, rightPointer = 0, mainPointer = 0;

        // Compare the values in each array and store them in increasing order
        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] <= right[rightPointer]) {
                arr[mainPointer++] = left[leftPointer++];
            } else {
                arr[mainPointer++] = right[rightPointer++];
            }
        }

        // Store any remaining from the left array
        while (leftPointer < left.length) {
            arr[mainPointer++] = left[leftPointer++];
        }

        // Or store any remaining from the right array
        while (rightPointer < right.length) {
            arr[mainPointer++] = right[rightPointer++];
        }
    }
}
