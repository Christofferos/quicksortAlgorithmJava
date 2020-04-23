/**
 * A simple class that implements the interface IntSorter. 
 *
 * @author Kristopher Werlinder
 * @version 2019-02-14
 */
import java.util.Arrays;
public class ArraysClass implements IntSorter {
    public void sort(int[] v) {
        Arrays.sort(v);
    }
}
