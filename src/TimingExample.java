import java.util.ArrayList;
import java.util.Arrays;

/**
 * An example demonstrating the effects of just-in-time compilation
 * on time measurements.
 *
 * @author Kristopher Werlinder
 * @version 2019-02-14
 */
public final class TimingExample {
    /**
     * If you're using a JVM with just-in-time compilation,
     * chanses are that the first reported time is much
     * larger than the rest: during most of the first
     * invocation of the sum() method, the code has yet
     * to be compiled and optimized.
     */
    public static void main(String[] args) {
        final int reps = 10;
        final int n = 1000000;
        final Stopwatch clock = new Stopwatch();

        // Discard results that are anomalous or affected by JVM warmup.
        int warmupRepetitions = 10;
        for (int k = 0; k < warmupRepetitions; k++) {
            long dummy = sum(n);
        }

        ArrayList<IntSorter> quickSorts = new ArrayList<>();
            //quickSorts.add(new InsertionSort());
            quickSorts.add(new QuicksortFixedPivot());
            quickSorts.add(new QuicksortFixedPivotInsertion());
            quickSorts.add(new QuicksortRandomPivot());
            quickSorts.add(new QuicksortRandomPivotInsertion());
            quickSorts.add(new QuickSort());
            quickSorts.add(new ArraysClass());

        ArrayList<String> namesForQuicksorts = new ArrayList<>();
            //namesForQuicksorts.add("Insertion Sort: ");
            namesForQuicksorts.add("QuicksortFP: ");
            namesForQuicksorts.add("QuicksortFPI: ");
            namesForQuicksorts.add("QuicksortRP: ");
            namesForQuicksorts.add("QuicksortRPI: ");
            namesForQuicksorts.add("QuicksortOptimized: ");
            namesForQuicksorts.add("Java Utils Arrays.sort: ");


        Data data = new Data(n, 10000000, Data.Order.ASCENDING);

        int nameCounter = 0;
        for (IntSorter quickSort : quickSorts) {
            // initialize the variables for every new sorting algorithm
            double min = 0, max = 0;
            double mean = 0;

            // print the label of the algorithm
            System.out.println(namesForQuicksorts.get(nameCounter));

            for (int i = 0; i < reps; i++) {

                int[] arrayCopy = data.get();

                clock.reset().start();
                {
                    quickSort.sort(arrayCopy);
                }
                long time = clock.stop().milliseconds();

                // initialize min and max
                if (max == 0 && min == 0) {
                    max = time;
                    min = time;
                    mean = time;
                } else {
                    mean += time;
                    // Get max and min
                    if (time > max) {
                        max = (double) time;
                    } else if (time < min) {
                        min = (double) time;
                    }
                }
                // System.out.printf("Time to run sorting algorithm(%d): %d ms%n", n, time);
            }

            // print out the variables for a specific sorting algorithm and increment nameCounter.
            mean = mean / reps;
            System.out.println("Average running time: " + mean + " ms");
            System.out.println("Max running time: " + max + " ms");
            System.out.println("Min running time: " + min + " ms");
            System.out.println();
            nameCounter++;
        }

    }



    /** Is used for warm up of JVM.
     * Returns the sum 1 + 2 + ... + n.
     */
    private static long sum(int n) {
       long sum = 0;  for (int i = 1; i <= n; i++) {
           sum += i;
       }
       return sum;
   }
}
