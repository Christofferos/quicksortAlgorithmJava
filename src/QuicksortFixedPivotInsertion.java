import java.util.Arrays;
// A fixed pivot with cut-off to insertion sort at k. (So from recursion quicksort to insertion sort, k = 20 maybe).
// Each Quicksort algorithm variation should be a single class that implements the IntSorter interface.
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */

public class QuicksortFixedPivotInsertion extends PartitionSwap implements IntSorter {

  /**
   * Method needed since class implements IntSorter.
   * @param int[] v.
   */
    public void sort(int[] v) {
        quicksortFPI(v, 0, v.length - 1);
    }

    /**
     * Method to sort int array.
     * @param int[] array, int left, right.
     */
    public void quicksortFPI(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        if (right - left + 1 < 10) {
            insertionSort(array, left, right);
        }

        int pivot = array[(right+left)/2];
        int index = partition(array, left, right, pivot);
        quicksortFPI(array, left, index - 1);
        quicksortFPI(array, index, right);

    }

    public static void insertionSort(int[] A, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int x = A[i];

            // find location to insert using binary search.
            int j = Math.abs(Arrays.binarySearch(A, left, i, x) + 1);

            // shifting array to one location right.
            System.arraycopy(A, j, A, j+1, i-j);

            // placing element at its correct location
            A[j] = x;
        }
    }
}
