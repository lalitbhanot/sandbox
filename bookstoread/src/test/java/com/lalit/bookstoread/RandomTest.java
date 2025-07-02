package com.lalit.bookstoread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.internal.Function;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName(" Class to test random features")
public class RandomTest {
    @Test
    void shouldCheckForEvenNumbers() {
        int number = new Random(10).nextInt();
        assertTrue(() -> number % 2 == 0, number + " is not an even number.");

        BiFunction<Integer, Integer, Boolean> divisible = (x, y) -> x % y == 0;
        Function<Integer, Boolean> multipleOf2 = (x) -> divisible.apply(x, 2);
        assertTrue(() -> multipleOf2.apply(number), () -> " 2 is not factor of " + number);

        List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 2);
        assertTrue(() -> numbers.stream().distinct().anyMatch(n -> n % 2 == 0), "Did not find an even number in the list");
    }

    static boolean isEven(int number) {
        return number % 2 == 0;
    }

    @Test
    void test_should_complete_in_one_second() {
        assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(5));
    }


    @RepeatedTest(10)
    void i_am_a_repeated_test() {
        assertTrue(true);
    }
}