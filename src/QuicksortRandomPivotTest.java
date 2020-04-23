import org.junit.Test;
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortRandomPivotTest extends IntSorterTest {
    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortRandomPivot();
    }

    @Test
    public void testConstructor() {
    }
}
