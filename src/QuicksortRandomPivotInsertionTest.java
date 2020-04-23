import org.junit.Test;
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortRandomPivotInsertionTest extends IntSorterTest {
    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortRandomPivotInsertion();
    }

    @Test
    public void testConstructor() {
    }
}
