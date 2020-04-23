import java.util.Arrays;
/*
  Insertion sort class. Warm up. Is linear time complexity for almost sorted arrays.
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class InsertionSort implements IntSorter {

    public void sort(int[] A) {
        insertionSort(A);
        //insertionSort(A, 0, A.length - 1);
    }

    /**
     * Normal insertionSort, can only handle whole arrays.
     *
     * @param int[] array.
     */
    public static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int j = i;
            while (j > 0 && A[j - 1] > A[j]) {
                int temp = A[j];
                A[j] = A[j - 1];
                A[j - 1] = temp;
                j = j - 1;
            }
        }
    }
}
