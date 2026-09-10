package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LongestSubarrayViaSingleElementEvictionTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and Single Element Arrays)
                Arguments.of(null, 0),
                Arguments.of(new int[] {}, 0),
                Arguments.of(new int[] { 1 }, 0), // Must delete the single 1 -> resulting array is empty, length 0
                Arguments.of(new int[] { 0 }, 0), // Must delete the single 0 -> resulting array is empty, length 0
                // 2. Arrays containing only 1s or only 0s
                Arguments.of(new int[] { 1, 1, 1 }, 2), // Delete one 1 -> remaining continuous 1s length is 2
                Arguments.of(new int[] { 0, 0, 0 }, 0), // Delete one 0 -> remaining are still zeros, length 0
                // 3. Single Zero Traps (Direct bridging)
                Arguments.of(new int[] { 1, 1, 0, 1 }, 3), // Evict 0 at idx 2 -> joins 1s into length 3
                Arguments.of(new int[] { 0, 1, 1, 1 }, 3), // Evict 0 at idx 0 -> remaining 1s length is 3
                Arguments.of(new int[] { 1, 1, 1, 0 }, 3), // Evict 0 at idx 3 -> remaining 1s length is 3
                // 4. Multiple Zeros (Choosing the optimal single deletion window)
                // Evicting 0 at idx 2 yields [1,1,_,1] -> length 3. Evicting 0 at idx 4 yields
                // [_,1,_,1,1] -> length 3.
                Arguments.of(new int[] { 1, 1, 0, 1, 0, 1, 1 }, 3),
                // Evicting 0 at idx 1 bridges nothing. Evicting 0 at idx 5 bridges 3 ones and 2
                // ones -> length 5.
                Arguments.of(new int[] { 1, 0, 1, 1, 1, 0, 1, 1 }, 5),
                // 5. Consecutive Zeros (Plateaus of Zeros)
                // Evicting either 0 at idx 1 or 2 still leaves a zero blocking the connection
                // -> max length is 1
                Arguments.of(new int[] { 1, 0, 0, 1 }, 1));
    }

    @ParameterizedTest(name = "Test {index}: Array={0} expected maximum length={1}")
    @MethodSource("testData")
    void testGetMaxSubarrayLength(int[] input, int expected) {
        Assertions.assertEquals(expected, LongestSubarrayViaSingleElementEviction.getMaxSubarrayWithPointers(input));
        Assertions.assertEquals(expected,
                LongestSubarrayViaSingleElementEviction.getMaxSubarrayWithSlidingWindow(input));
    }
}
