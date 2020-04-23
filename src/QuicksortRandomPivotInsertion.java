import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// A random pivot with cut-off to insertion sort at k.
// Each Quicksort algorithm variation should be a single class that implements the IntSorter interface.
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortRandomPivotInsertion extends PartitionSwap implements IntSorter {
  /**
   * Method needed since class implements IntSorter.
   * @param int[] v.
   */
    public void sort(int[] v) {
        quicksortRPI(v, 0, v.length - 1);
    }

    /**
     * Method to sort int array.
     * @param int[] array, int left, right.
     */
    public void quicksortRPI(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }

        if (right - left + 1 < 10) {
            insertionSort(array, left, right);
        }

        int randomNum = ThreadLocalRandom.current().nextInt(left, right);
        int pivot = array[randomNum];
        int index = partition(array, left, right, pivot);
        quicksortRPI(array, left, index - 1);
        quicksortRPI(array, index, right);
    }

    /**
     * Method to insertSort int array.
     * @param int[] array, int left, right.
     */
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
