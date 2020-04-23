import java.util.Arrays;
/*
Quicksort to sort an integer array in acending order. Implements the interface IntSorter!
* @author Kristopher Werlinder
* @version 2019-02-14
*/
public class QuickSort implements IntSorter {
    public void sort(int[] v) {
        quicksortFPI(v, 0, v.length - 1);
    }

    /**
     * Method to execute the sorting of the int array. No return needed.
     * @param int[] array, int left, right.
     */
    private static void quicksortFPI(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        if (right - left + 1 <= 10) {
            // Enhanced Insertion Sort
            for (int i = left + 1; i <= right; i++) {
                int x = a[i];

                // find location to insert using binary search.
                int j = Math.abs(Arrays.binarySearch(a, left, i, x) + 1);

                // shifting array to one location right.
                System.arraycopy(a, j, a, j+1, i-j);

                // placing element at its correct location
                a[j] = x;
            }
            return;
        }
        int pivot = ninther(a, left, right);
        PartitionResult res = partition(a, left, right, pivot);
        quicksortFPI(a, left, res.lo - 1);
        quicksortFPI(a, res.hi + 1, right);
    }

    /**
     * Method to find median of 3. Good for pivot element choice!
     * @param int[] array, int i0, i1, i2.
     * @return int "pivot".
     */
    private static int med3(int[] a, int i0, int i1, int i2) {
        return med3(a[i0], a[i1], a[i2]);
    }

    private static int med3(int a0, int a1, int a2) {
        if (a0 < a1) {
            // a0 a1 a2
            // a0 a2 a1
            // a2 a0 a1
            if (a0 > a2) {
                // a2 a0 a1
                return a0;
            } else {
                // a0 a1 a2
                // a0 a2 a1
                return a1 < a2 ? a1 : a2;
            }
        }
        else {
            // a1 a0 a2
            // a1 a2 a0
            // a2 a1 a0
            if (a0 < a2) {
                // a1 a0 a2
                return a0;
            }
            else {
                // a1 a2 a0
                // a2 a1 a0
                return a2 > a1 ? a2 : a1;
            }
        }
    }

    /**
     * Method to find the pivot element in aid of median of 3.
     * @param int[] array, int lo, hi.
     * @return int "pivot".
     */
    private static int ninther(int[] a, int lo, int hi) {
        if (hi == lo || hi == lo+1) {
            return a[lo];

        } else if (hi == lo+2) {
            return med3(a, lo, lo+1, lo+2);

        } else {
            int m3 = (hi - lo + 1) / 3;
            return med3(
                    med3(a, lo,     lo+m3/2,     lo+m3-1),
                    med3(a, lo+m3,   lo+m3+m3/2,   hi-m3-1),
                    med3(a, hi-m3,   hi-m3/2,     hi)
            );
        }
    }

    /**
     * Class to hold low and high values.
     * @param int lo, hi.
     */
    static class PartitionResult {
        int lo, hi;

        /**
         * Constructor initialize lo and hi.
         * @param int lo, hi.
         */
        PartitionResult(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    //
    /**
     * Placement pointer to remember where our placement go, do forward and backwards scan.
     * @param int[] array, int lo, hi, pivot.
     * @return object PartitionResults.
     */
    static private PartitionResult partition(int[] array, int lo, int hi, int pivot) { //return int before
        int mid = lo;

        while(mid <= hi) {
            if(array[mid] < pivot) {
                    int tempLeft = array[lo];
                    array[lo] = array[mid];
                    array[mid] = tempLeft;
                    lo++;
                    mid++;

            }
            else if(array[mid] > pivot) {
                    int tempLeft = array[mid];
                    array[mid] = array[hi];
                    array[hi] = tempLeft;
                    hi--;

            }
            else { // array[mid] == pivot
                mid++;
            }
        }
        return new PartitionResult(lo, hi);
    }

}
