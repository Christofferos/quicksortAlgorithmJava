# A Study of Quicksort

Author: Kristopher Werlinder <br>
INDA Group: Gustaf Gunérs Group <br>
Year: March in 2019

## Characteristics and Complexity
\\	In this section, briefly discuss:
* Origins of Quicksort
    A British man called Tony Hoare, a computer scientist, developed the Quicksort algorithm in 1959.
    Hoare needed an algorithm for sorting words in Russian sentences to be able to translate them faster to English through a
    Russian-English dictionary.   
* Its characteristics
    Quicksort is a divide and conquer algorithm, a systematic method for sorting elements in ascending order in an array. It is a comparison sort type algorithm, which means it is
    intended to work with items that have defined size relationship between eachother. A charactistic of Quicksort is that
    it does not require large amounts of memory, it operates on arrays in-place. Important concepts of Quicksort
    are pivot elements, partitioning scheme and recursive funcunality that at small enough sizes goes over to an iterative approach.
* Its runtime complexity
    Quicksort is an O(n log n) efficient sorting algorithm, if implemented good enough it can work with twice or three times the speed of
    normal merge sort and heapsort. However, with a bad implemented version or in rare cases the worst case time complexity can reach O(n^2).

## Variations of Quicksort

In this section:
* Outline each variation you implemented <br>

**QuicksortFixedPivot:** <br>
    Implements the IntSorter interface with an implementation for the sort(int[] v) method,
    in this method body function quicksortFP(v, 0, v.length - 1) is invoked. This quicksortFP starts of by
    checking if 0 >= v.length-1 if so it is done sorting, otherwise it continues by choosing pivot element as
    the middle element in the input array (array[(left + right) / 2]). Partitioning method is invoked from the
    superclass PartitionSwap that QuicksortFixedPivot class extends from (this is to avoid code dublication
    in quicksort varaitions). Partitioning method goes through all elements from left to right (end) and then finishes by
    putting pivot at the right position, now we have pivot on the right position and elements smallar than pivot to left and
    elements greater than pivot to right of pivot. A index number for the pivot element is returned - this number
    is used to split the input array into two smaller subarrays, one with smaller elements than pivot and one with greater elements than pivot.
    Then, these two subarrays are sorted by recursion (of quicksortFP).
<br>
**QuicksortFixedPivotInsertion:** <br>
    Works in similar fashion as QuickSortFixedPivot and is essentially just an extension. QuicksortFixedPivotInsertion
    uses an iterative approach for sorting elements for small enough arrays. For this, insertion sort is used -
    reason is that recurrsion is slow for small inputs compared to iterative funcitons.
    I have tested different thresholds but found that 64 worked well, so I went with that.
<br>
**QuicksortRandomPivot:** <br>
    Chooses the pivot element at random to avoid "targeted attacks". Bad choices of pivot is avoided because there
    is randomness in the choosing process. This makes the implementation less prone to worse case runtime O(n^2). Random number
    is generated using ThreadLocalRandom which is a import of a java.util package I am making use of.
<br>
**QuicksortRandomPivotInsertion:** <br>
    This converts to normal insertionsort after certain size of array. Which increases speed compared to QuicksortRandomPivot.
<br>
* **Include snippets of code where relevant** (e.g. different partition routines)
    Here are some different partition methods I implemented successfully:

                1.      public int partition(int[] array, int left, int right, int pivot) {
                            while(left <= right) {
                                while (array[left] < pivot) {
                                    left++;
                                }

                                while (array[right] > pivot) {
                                    right--;
                                }

                                if(left <= right) {
                                    swap(array, left, right);
                                    left++;
                                    right--;
                                }
                            }

                            return left;
                        }

                2.      static private PartitionResult partition(int[] array, int lo, int hi, int pivot) { //return int before
                            int mid = lo;

                            while(mid <= hi) {
                                if(array[mid] < pivot) {
                                    //swap(array, lo, mid);   // if(array[lo] != array[mid])
                                    int tempLeft = array[lo];
                                    array[lo] = array[mid];
                                    array[mid] = tempLeft;
                                    lo++;
                                    mid++;
                                }
                                else if(array[mid] > pivot) {
                                    //swap(array, mid, hi);   // if(array[mid] != array[hi])
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

            Here are some pivot methods I implemented successfully (with included med3):

                1.     private static int ninther(int[] a, int lo, int hi) {
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

                2.     ThreadLocalRandom.current().nextInt(left, right);

                3.     array[(left + right) / 2];


#### Here are some insertion sort implementations I tried using:

                1.
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


                2.
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


* **Discuss your cut-off strategy** <br>
    To avoid excess recurrsion calls I used a cut off strategy, to change the recurrsion into interation when
    the array size was smaller than my particular threshold. This threshold was 10 elements for me, it turned out to give the
    best running time for random arrays. However, the main idea and concept here is to change from Quicksort's recurrsion
    to Insertion Sort's interative approach for sorting arrays, just to optimize the run time in the last steps of sorting.  


## Methodology

**In this section, describe the tests you performed, making reference to:**
* **Test data**
  were of different types: random, ascending, descending and arrays with equal elements.
  This was to test how the sorting algorithm would do in different enviorments of usage.

	- **Even and ascendingordered arrays were**
	tested simultaniously to check for a edge-case, that is if the array is already sorted - the Quicksort should in that case not
	change anything.
	- **Odd array with decending order** is to check that no buggs surrounding inclusivity or exclusivity are generated by an array of odd length and also
	to make sure a completly reveresed array is sorted to ascending order after the process has been done in Quicksort.
	- **Test that random arrays are being sorted correctly** is just essential to make sure the general functionality for quicksort is in place.
	- **Test for arrays with equal elements** is just another edge case test that involves making sure the quicksort does not affect the order of the array.
	- **Test of huge arrays** is to make sure it is fast enough for big data input.
	- **Test for sorting of an empty array** is to check for another edge case to see that is works properly.
* **Problem size** <br>
  Some tests had huge data input to test the sorting algorithms functionality for large data, some had size zero and in other tests there
	were sizes of 3, 4, 6, 10.  
* **Algorithms used** <br>
	for the tests could have been "streams", but I chose to use simple for loops and assertcalls to make it simple since there were not so many tests in total.
	Most methods that were neccessary for all set ups pre-asserting, could be found in the Data class where copies of
  arrays of varied sort were available.


## Results

**In this section:**
  * **Present a table for the data from each test** <br>

**Random data:**

|Problem Size   |InsertionSort   |QuicksortFixedPivot   |QuicksortFixedPivotInsertion   |QuicksortRandomPivot   |QuicksortRandomPivotInsertion |5th QuickSort   | Arrays.sort |
|---|---|---|---|---|---|---|---|
|100   |0.003 ms   |0.004 ms |7.0E-4 ms   |0.001 ms   |0.019 ms   |0.003 ms   | 0.0 ms |
|1000   |0.026 ms   |0.0014 ms   |0.0017 ms   |0.009 ms   |0.01 ms  |0.003 ms   | 0.003 ms |
|10000   |2.195 ms   |0.0089 ms   |0.0061 ms  |0.316 ms  |0.93 ms   |0.362 ms   |0.15 ms |
|100000   |297.86 ms   |7.482 ms   |8.65 ms   |8.889 ms   |11.85 ms   |11.7 ms   | 5.414 ms |
|1000000   |35931 ms   |98.8 ms   |103.9 ms  |114.2 ms  |135.8 ms   |142.0 ms  | 93.3 ms|


**Sorted Data:**

|Problem Size   |InsertionSort   |QuicksortFixedPivot   |QuicksortFixedPivotInsertion   |QuicksortRandomPivot   |QuicksortRandomPivotInsertion |5th QuickSort   | Arrays.sort |
|---|---|---|---|---|---|---|---|
|100   |0.0 ms   |0.03 ms |0.0 ms   |0.0 ms   |0.0 ms   |0.0 ms   |0.0 ms  |
|1000   |0.0 ms   |0.03 ms  |0.0 ms   |0.01 ms   |0.05 ms  |0.0 ms   | 0.0 ms |
|10000   |0.0 ms   |0.01 ms  |0.02 ms  |0.03 ms  | 0.2 ms   |0.05 ms   |0.0 ms |
|100000   |0.1 ms  |1.38 ms   |13.42 ms  |3.47 ms   |5.45 ms   |3.65 ms   | 0.02 ms |
|1000000   |0.9 ms   |18.0 ms   |32.8 ms  |39.5 ms  |59.4 ms   | 53.4 ms  | 11.3 ms|

**Reversed Data:**

|Problem Size   |InsertionSort   |QuicksortFixedPivot   |QuicksortFixedPivotInsertion   |QuicksortRandomPivot   |QuicksortRandomPivotInsertion |5th QuickSort   | Arrays.sort |
|---|---|---|---|---|---|---|---|
|100   | 0.0 ms   |0.0 ms |0.0 ms   |0.0 ms  |0.0 ms   |0.0 ms   |0.0 ms  |
|1000   |0.01 ms   |0.0 ms  |0.0 ms   |0.1 ms   |0.2 ms  |0.0 ms   | 0.0 ms |
|10000   |5.52 ms   |0.1 ms  |0.02 ms  |0.3 ms  |1.0 ms   |0.6 ms   |0.0 ms |
|100000   |700.8 ms  |2.6 ms   |2.6 ms  |5.5 ms   | 7.2 ms   |5.3 ms   | 0.3 ms |
|1000000   |...   |19.3 ms   |22.0 ms  |38.6 ms  |61.8 ms   | 54.3 ms  | 2.0 ms|

**Equal Data:**

|Problem Size   |InsertionSort   |QuicksortFixedPivot   |QuicksortFixedPivotInsertion   |QuicksortRandomPivot   |QuicksortRandomPivotInsertion |5th QuickSort   | Arrays.sort |
|---|---|---|---|---|---|---|---|
|100   |0.0 ms  |0.0 ms |0.0 ms   |0.0 ms  |0.0 ms   |0.0 ms   |0.0 ms  |
|1000   |0.0 ms   |0.0 ms  |0.0 ms   |0.01 ms  |0.01 ms  |0.0 ms   |0.0 ms  |
|10000   |0.0 ms   |0.5 ms  |0.0 ms  |0.2 ms  |0.2 ms   |0.1 ms   |0.0 ms |
|100000   |0.2 ms  |3.0 ms   |1.2 ms  |3.5 ms   |4.9 ms    |0.2 ms   |0.5 ms  |
|1000000   |0.8 ms   |22.1 ms   |13.8 ms  |22.9 ms  |35.2 ms   |0.9 ms   |8.6 ms  |

 (For equal data in the last
      table I switched to normal insertion which made it a lot faster than it would be than
      my own binarySearch type Insertionsort).

  * **Generate charts (use Matlab or another tool) to show the data**

![alt text](https://gits-15.sys.kth.se/inda-18/kriwer-quicksort/raw/master/docs/randomdata.png)

![alt text](https://gits-15.sys.kth.se/inda-18/kriwer-quicksort/raw/master/docs/sorteddata.png)

![alt text](https://gits-15.sys.kth.se/inda-18/kriwer-quicksort/raw/master/docs/reverseddata.png)

![alt text](https://gits-15.sys.kth.se/inda-18/kriwer-quicksort/raw/master/docs/equaldata.png)

![](assets/report-cb1781e9.png)

![](assets/report-fef951f0.png)

![](assets/report-5b29fd5a.png)

![](assets/report-0290e93f.png)



* **Ensure you label each clearly** <br>
Yes, I got it!


## Discussion

**In this section:**
  * **Discuss your general findings based on the data** <br>
      The most shocking finding was the 0.8 ms running time for insertion sort on 1 000 000 equal elements which
      compared to Java's own Arrays.sort is much better, it took 8.6 ms. This just shows how well suited insertion sort is
      to use and switch to in a sorting algorithm if the element equality pattern, in an array, can be determined from a small sample size.
      And the same goes for arrays with elements in almost sorted order, is of linear time when used with insertion sort... very fast: 1 000 000 elements in
      ascending order took 0.9 ms for insertion sort compared to Arrays.sort's 11.9 ms!
      Intresting is to see the catostrofical effect insertion sort can have if it is used on arrays with random elements
      or when elements are in reversed order, which of course are large enough.

  * **What was surprising** <br>
      Another suprising and sad finding was that my optimized quicksort class was slower at
      sorting arrays in general. The basic implementation worked better as a sorting algorithm.
  * **What met your expectations** <br>
      The insertion sort met my expectations with being fast on sorted data and equal data! But at the same time
      it was faster than I would have guessed. However, all insertion variations did not meet my expectations. I thought
      these implementations would go much faster than their implementations without it.
  * **Which variation was closet to Arrays.sort?** <br>
      The variation closest to Arrays.sort timewise was QuickSortFixedPivot variation. But implementation-wise would probably be
      my optimized Quicksort (5th) because it uses - ninether method for finding a good choice of pivot and
      dutch-flag-implementation that returns two indexes for partitioning.
