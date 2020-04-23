import org.junit.Test;
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortFixedPivotInsertionTest extends IntSorterTest {
    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortFixedPivotInsertion();
    }

    @Test
    public void testConstructor() {
    }
}
