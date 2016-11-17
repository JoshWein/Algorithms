import java.util.Arrays;

/**
 * Created by Josh Wein
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] testArray = {5, 2, 7, 1, 3, 5, 4, 2, 17, 9};
        System.out.println(Arrays.toString(selectionSortGreedy(testArray)));
    }

    public static int[] selectionSortGreedy(int[] arr) {
        // iterate up to the second to last element in the array
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // For each element, iterate from the next element to the end of the array
            for (int j = i + 1; j < arr.length; j++) {
                // Find the lowest element in the right side of the array
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(i, min, arr);
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
