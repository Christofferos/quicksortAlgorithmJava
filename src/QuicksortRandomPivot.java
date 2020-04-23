import java.util.concurrent.ThreadLocalRandom;
// uses a randomly selected pivot
// Each Quicksort algorithm variation should be a single class that implements the IntSorter interface.
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortRandomPivot extends PartitionSwap implements IntSorter {
  /**
   * Method needed since class implements IntSorter.
   * @param int[] v.
   */
    public void sort(int[] v) {
        quicksortRP(v, 0, v.length - 1);
    }

    /**
     * Method to sort int array.
     * @param int[] array, int left, right.
     */
    public void quicksortRP(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }
        int randomNum = ThreadLocalRandom.current().nextInt(left, right);
        int pivot = array[randomNum];
        int index = partition(array, left, right, pivot);
        quicksortRP(array, left, index - 1);
        quicksortRP(array, index, right);
    }
}
