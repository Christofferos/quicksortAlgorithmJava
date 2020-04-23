import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

// "It is more important that you structure it well than that you cover every single corner case imaginable."
/**
 * Abstract test class for  implementations.
 *
 * Implementing test classes must override the getIntSorter method.
 *
 * @author Kristopher Werlinder
 * @version 2019-02-14
 */
// OBS!! For the most basic configuration, put all your tests in IntSorterTest.
    /*
        You will want to test the following types of arrays:
        Arrays of even and odd length
        Sorted in ascending order
        Sorted in descending order (reversed)
        Random
        All elements equal
        Very large arrays (you may want to exclude insertion sort from some of these)

 OBS!!! Use the provided Data class to generate these arrays! The main method in said class has some examples of how to use it.
     */

public abstract class IntSorterTest {
    /* Instantiate IntSorter*/
    private IntSorter s1;

    /* An array of even length */
    private int[] evenArrayAscendingOrder;
    /* An array of odd length */
    private int[] oddArrayDescendingOrder;
    /* An array with equal elements */
    private int[] arrayWithRandomElements;
    /* An array with equal elements */
    private int[] arrayWithEqualElements;
    /* An array with very, very many elements */
    private int[] hugeArray;
    /* An array with no elements */
    private int[] emptyArray;


    /**
     * Returns an implementation of the IntSorter interface. Extending classes
     * must override this method.
     *
     * @return An implementation of Set.
     */

    protected abstract IntSorter getIntSorter();

    @Before
    public void setUp() {
        s1 = getIntSorter();

        evenArrayAscendingOrder = new Data(4, 20, Data.Order.ASCENDING).get();
        oddArrayDescendingOrder = new Data(3, 20, Data.Order.DESCENDING).get();
        arrayWithRandomElements = new Data(10, 20, Data.Order.RANDOM).get();
        arrayWithEqualElements = new Data(6, 1, Data.Order.RANDOM).get();
        hugeArray = new Data(10000, 100, Data.Order.RANDOM).get();
        emptyArray = new Data(0, 20, Data.Order.RANDOM).get();
    }

    /**
     * Tests for arrays of even length.
     * Tests for arrays sorted in ascending order.
     */
    @Test
    public void orderIsCorrectWhenInputArrayIsEvenAndInAscendingOrder() {
        // Arrange
            int size = evenArrayAscendingOrder.length;
            int[] tempArray = Arrays.copyOf(evenArrayAscendingOrder, size);
        // Act
            s1.sort(tempArray);
        // Assert
            int i = 0;
            for (int element : evenArrayAscendingOrder) {
                assertThat(tempArray[i] == element, equalTo(true));
                i++;
            }
    }

    /*
    * Tests for arrays of odd length.
    * Tests for arrays sorted in descending order (reversed).
    */
    @Test
    public void orderIsCorrectWhenInputArrayIsOddAndInDescendingOrder() {
        // Arrange
            int size = oddArrayDescendingOrder.length;
            int[] tempArray = Arrays.copyOf(oddArrayDescendingOrder, size);
        // Act
            s1.sort(tempArray);
        // Assert
            for (int i = 0; i < size; i++) {
                assertThat(tempArray[i] == oddArrayDescendingOrder[size-i-1], equalTo(true));
            }
    }


    /**
     * Tests for random arrays.
     */
    @Test
    public void arrayWithRandomElementsIsInAscendingOrderAfterSort() {
        // Arrange
            int size = arrayWithRandomElements.length;
            int[] tempArray = Arrays.copyOf(arrayWithRandomElements, size);
        // Act
            s1.sort(tempArray);
        // Assert
            for (int i = 0; i < size-1; i++) {
                assertThat(tempArray[i] <= tempArray[i+1], equalTo(true));
            }
    }


    /**
     * Tests for arrays with all elements equal.
     */
    @Test
    public void arrayWithEqualElementsIsUnchangedAfterSort() {
        // Arrange
            int size = arrayWithEqualElements.length;
            int[] tempArray = Arrays.copyOf(arrayWithEqualElements, size);
        // Act
            s1.sort(tempArray);
        // Assert
            for (int i = 0; i < size; i++) {
                assertThat(tempArray[i] == arrayWithEqualElements[i], equalTo(true));
            }
    }


    /**
     * Tests for very large arrays.
     */
    @Test
    public void orderIsAscendingWhenInputArrayIsHuge() {
        // Arrange
            int size = hugeArray.length;
            int[] tempArray = Arrays.copyOf(hugeArray, size);
        // Act
            s1.sort(tempArray);
        // Assert
            for (int i = 0; i < size-1; i++) {
                assertThat(tempArray[i] <= tempArray[i+1], equalTo(true));
            }
    }


    /**
     * Tests for empty arrays.
     */
    @Test
    public void sortFunctionHandlesEmptyArraysWithoutErrors() {
        // Arrange
            int size = emptyArray.length;
            int[] tempArray = Arrays.copyOf(emptyArray, size);
        // Act
            s1.sort(tempArray);
            String arrayString = null;
        // Assert
            assertThat(tempArray.length == emptyArray.length, equalTo(true));

    }

}
