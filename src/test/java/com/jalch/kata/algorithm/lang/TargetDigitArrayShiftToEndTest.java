package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TargetDigitArrayShiftToEndTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, 7, null),
                Arguments.of(new int[] {}, 7, new int[] {}),
                Arguments.of(new int[] { 7 }, 7, new int[] { 7 }),
                Arguments.of(new int[] { 7, 7 }, 7, new int[] { 7, 7 }),
                Arguments.of(new int[] { 7, 7, 7 }, 7, new int[] { 7, 7, 7 }),
                Arguments.of(new int[] { 7, 7, 7, 1 }, 7, new int[] { 1, 7, 7, 7 }),
                // 1. Target is Absent
                Arguments.of(new int[] { 1, 2, 3, 4 }, 7, new int[] { 1, 2, 3, 4 }),
                // 2. Target Alternating / Dispersed
                Arguments.of(new int[] { 7, 1, 7, 2, 7, 3 }, 7, new int[] { 1, 2, 3, 7, 7, 7 }),
                // 3. Targets All at the Front
                Arguments.of(new int[] { 7, 7, 7, 4, 5, 6 }, 7, new int[] { 4, 5, 6, 7, 7, 7 }),
                // 4. Targets All at the Back (Should trigger minimal/no writes)
                Arguments.of(new int[] { 1, 2, 3, 7, 7, 7 }, 7, new int[] { 1, 2, 3, 7, 7, 7 }),
                // 5. Multiple Distinct Non-Target Values
                Arguments.of(new int[] { 10, 7, 20, 7, 30, 40 }, 7, new int[] { 10, 20, 30, 40, 7, 7 }),
                // 6. Negative Integers and Zero Bounds
                Arguments.of(new int[] { -1, -7, 0, -7, 5 }, -7, new int[] { -1, 0, 5, -7, -7 }),
                // 7. Large Clustered Shifts
                Arguments.of(new int[] { 7, 7, 1, 1, 7, 7 }, 7, new int[] { 1, 1, 7, 7, 7, 7 }));
    }

    @ParameterizedTest(name = "Test {index}: Array={0} and target={1} results in={2}")
    @MethodSource("testData")
    void testFindAndFindOptimised(int[] input, int target, int[] expected) {
        // int[] inputClone = input.clone();
        TargetDigitArrayShiftToEnd.shift(input, target);
        Assertions.assertArrayEquals(expected, input);
    }
}
