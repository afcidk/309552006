import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
            Arguments.of(new Integer[]{1,3,2}, new Integer[]{1,3,4}),
            Arguments.of(new Integer[]{777}, new Integer[]{777}),
            Arguments.of(new Integer[]{2,7,4}, new Integer[]{2,4,7}),
            Arguments.of(new Integer[]{}, new Integer[]{}),
            Arguments.of(new Integer[]{1,7,1,0}, new Integer[]{0,1,1,7})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("stringIntAndListProvider")
    public void TestPriorityQueue(Integer[] random_array, Integer[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        for (Integer n: random_array) test.add(n);
        Integer[] result = new Integer[random_array.length];

        int i=0;
        while (!test.isEmpty()) {
            result[i] = test.poll();
            i += 1;
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void TestInsertNull() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }

    @Test
    public void TestInitialCapacity_Less1() {
        assertThrows(IllegalArgumentException.class, () -> {
           PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void TestBadCast() {
        assertThrows(ClassCastException.class, () -> {
           PriorityQueue<int[]> a = new PriorityQueue<int[]>();
           int []a1 = new int[]{1,2};
           int []a2 = new int[]{9,9};
           a.add(a1);
        });
    }
}