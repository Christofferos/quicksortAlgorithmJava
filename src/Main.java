import java.util.ArrayList;

/** @author Kristopher Werlinder
* @version 2019-02-14
      Class to test the runtime of quicksort.
*/
public class Main {
    public static void main(String args[]) {

        ArrayList<IntSorter> quickSorts = new ArrayList<>();
        // Optimized QuickSort algorithm
        quickSorts.add(new QuickSort());

        // Fill array with random integers
        int size = 10000;

        // Test the run time for different QuickSort implementations.
        for (IntSorter quickSort : quickSorts) {
            Data data = new Data(size, 10000, Data.Order.RANDOM);

            // WARM UP repetitions!
            int warmupRepetitions = 10;
            for (int n = 0; n < warmupRepetitions; n++) {
                int[] array = data.get();
                quickSort.sort(array);
            }

            // Calculate average time.
            long testRepitions = 100;
            double avrTimeCost = 0;
            //TimingExample measuringClock = new TimingExample();

            for (int n = 0; n < testRepitions; n++) {
                int[] array = data.get();

                long t0 = System.nanoTime();

                quickSort.sort(array);

                long t1 = System.nanoTime();
                double timecost = t1 - t0;

                avrTimeCost += timecost;

                // Test that it is sorted
                for(int i = 1; i < array.length; i++) {
                    assert array[i] >= array[i-1];
                }
            }

            avrTimeCost = avrTimeCost / testRepitions;
            System.out.println("Running time: " + avrTimeCost + " ns");
        }

    }
}
