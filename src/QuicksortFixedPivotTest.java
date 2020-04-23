import org.junit.Test;
/*
* @author Kristopher Werlinder
* @version 2019-02-14
 */
public class QuicksortFixedPivotTest extends IntSorterTest {
    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortFixedPivot();
    }

    @Test
    public void testConstructor() {
    }
}
