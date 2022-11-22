package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class AppTest {
    // BEGIN
    @BeforeAll
    static void prepare() {
        System.out.println("Prepare workaround for tests.");

    }
    @Test
    void testTake() {
        //case: 1
        List<Integer> firstNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> firstExpected = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> firstResult = App.take(firstNumbers, 2);
        org.assertj.core.api.Assertions.assertThat(firstResult).isEqualTo(firstExpected);
        // case: 2
        List<Integer> secondNumbers = new ArrayList<>(Arrays.asList(7, 3, 10));
        List<Integer> secondResult = App.take(secondNumbers, 8);
        org.junit.jupiter.api.Assertions.assertEquals(secondNumbers, secondResult);
        //case: 3
        List<Integer> thirdExpected = new ArrayList<>(Arrays.asList(7, 3));
        List<Integer> thirdResult = App.take(secondNumbers, 2);
        org.junit.jupiter.api.Assertions.assertEquals(thirdExpected, thirdResult);
    }

    // END
}
