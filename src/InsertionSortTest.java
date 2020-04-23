import org.junit.Test;
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class InsertionSortTest extends IntSorterTest {

    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortFixedPivotInsertion();
    }

}
