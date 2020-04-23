/*
Parition and Swaping of elements step in the quicksort process.
* @author Kristopher Werlinder
* @version 2019-02-14
*/
public class PartitionSwap {

  /**
   * Partitioning step of an int array.
   *
   * @param int[] array, int left, right , pivot.
   * @return int left.
   */
    public int partition(int[] array, int left, int right, int pivot) {
        while(left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if(left <= right) {
                int tempLeft = array[left];
                array[left] = array[right];
                array[right] = tempLeft;
                left++;
                right--;
            }
        }
        return left;
    }
}
