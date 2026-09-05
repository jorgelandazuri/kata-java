package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.Arguments;

public class RemoveDuplicatedFromSortedArrayTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new int[] {}, 0),
                Arguments.of(new int[] { 1 }, 1),
                Arguments.of(new int[] { 1, 1, 2 }, 2),
                Arguments.of(new int[] { 1, 1, 2, 2, 2, 2, 2, 2, 2, 2 }, 2),
                Arguments.of(new int[] { -1, 1, 2, 3, 4, 4, 5, 10 }, 7),
                Arguments.of(new int[] { -1, -1, 2, 3, 4, 4, 5, 10, 10 }, 6),
                Arguments.of(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }, 5));
    }

    @ParameterizedTest(name = "For numbers={0}, expected is {1}")
    @MethodSource("testData")
    public void removedeplicated(int[] input, int expected) {
        Assertions.assertEquals(RemoveDuplicatedFromSortedArray.remove(input), expected);
    }
}
