package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MonotonicPeakFinderTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(new int[] {}, false),
                Arguments.of(new int[] { 1 }, false),
                Arguments.of(new int[] { 1, 2 }, false),
                Arguments.of(new int[] { 1, 2, 3 }, false),
                Arguments.of(new int[] { 1, 3, 2 }, true),
                Arguments.of(new int[] { 3, 2, 3 }, false),
                Arguments.of(new int[] { 3, 2, 1 }, false),
                Arguments.of(new int[] { 4, 2, 4, 3 }, false),
                Arguments.of(new int[] { 1, 2, 3, 4, 7, 10, 6, 3, 1 }, true),
                Arguments.of(new int[] { 1, 2, 3, 4, 7, 10, 10, 6, 3, 1 }, false),
                Arguments.of(new int[] { 1, 2, 3, 4, 7, 10, 6, 3, 1, 10 }, false),
                Arguments.of(new int[] { 10, 2, 3, 4, 7, 10, 6, 3, 1 }, false),
                Arguments.of(new int[] { 1, 2, 1, 3, 2 }, false),
                // 1. Boundary Values (Integer.MIN_VALUE & Integer.MAX_VALUE)
                Arguments.of(new int[] { Integer.MIN_VALUE, 0, Integer.MIN_VALUE }, true),
                Arguments.of(new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE }, false),
                Arguments.of(new int[] { 1, Integer.MAX_VALUE, 1 }, true),
                Arguments.of(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }, false),
                Arguments.of(new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE }, true),
                // 2. Strict Monotonic Failures (Plateaus)
                Arguments.of(new int[] { 1, 2, 2, 1 }, false), // Plateau exactly at the peak
                Arguments.of(new int[] { 1, 1, 2, 1 }, false), // Plateau on the uphill climb
                Arguments.of(new int[] { 1, 2, 1, 1 }, false), // Plateau on the downhill descent
                // 3. Fake Peaks & Valleys (Multiple Peaks)
                Arguments.of(new int[] { 1, 3, 2, 4, 1 }, false), // "M" shape (Two valid peaks with a valley)
                Arguments.of(new int[] { 1, 5, 4, 5, 2 }, false), // Peak, small dip, another peak
                Arguments.of(new int[] { 5, 1, 5 }, false), // "V" shape / Inverted mountain
                // 4. Large Structural Shifts
                Arguments.of(new int[] { 0, 100, 99, 98, 97, 96, 95 }, true), // Sharp peak followed by a long, slow
                Arguments.of(new int[] { 0, 1, 2, 3, 4, 100, 0 }, true), // Long, slow climb followed by a sharp drop
                // 5. Minimal Strict Failures
                Arguments.of(new int[] { 1, 2, 3, 2, 3 }, false), // Climbing up, dropping down, climbing back up at the
                                                                  // end
                Arguments.of(new int[] { 3, 2, 3, 2, 1 }, false) // Starting downhill, climbing up, dropping down
        );
    }

    @ParameterizedTest(name = "Test {index}: Array={0} is mountain={1}")
    @MethodSource("testData")
    void testFindAndFindOptimised(int[] input, boolean expected) {
        Assertions.assertEquals(expected, MonotonicPeakFinder.isMountainArrayFindingMax(input));
        Assertions.assertEquals(expected, MonotonicPeakFinder.isMountainArrayOnePass(input));
    }
}
