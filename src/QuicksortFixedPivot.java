// uses a fixed pivot
// Each Quicksort algorithm variation should be a single class that implements the IntSorter interface.

// Assistant's requirement You will probably find that you duplicate some code when writing the different Quicksort implementations.
// Make this duplication minimal. It's not okay to have four exact duplicates of the same partitioning algorithm.

/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortFixedPivot extends PartitionSwap implements IntSorter {

  /**
   * Method needed since class implements IntSorter.
   * @param int[] v.
   */
    public void sort(int[] v) {
        quicksortFP(v, 0, v.length - 1);
    }

    /**
     * Method to sort int array.
     * @param int[] array, int left, right.
     */
    public void quicksortFP(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }
        int pivot = array[(right+left)/2];
        int index = partition(array, left, right, pivot);
        quicksortFP(array, left, index - 1);
        quicksortFP(array, index, right);
    }
}
