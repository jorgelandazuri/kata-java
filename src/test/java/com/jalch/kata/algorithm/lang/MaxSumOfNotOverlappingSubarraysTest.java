package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MaxSumOfNotOverlappingSubarraysTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, or Array too small for both lengths combined)
                Arguments.of(null, 1, 2, 0),
                Arguments.of(new int[] {}, 1, 2, 0),
                Arguments.of(new int[] { 1, 2 }, 1, 2, 0), // Combined size (3) is larger than array size (2)
                // 2. Exact Array Size Match (No room to slide, must pick all elements)
                Arguments.of(new int[] { 3, 8, 1, 3, 2, 5, 8 }, 3, 4, 30), // 3+8+1 + 3+2+5+8 = 30
                Arguments.of(new int[] { 0, 6, 5, 2, 2, 5, 1, 9, 4 }, 1, 2, 20), // Picks elements for maximum
                                                                                 // combinations
                // 3. First Window Spans Before Second Window Order Priority
                // Array:, firstLen = 1, secondLen = 2
                // Optimal picks: "9" (len 1) and "5, 6" (len 2) or "6, 5" (len 2) -> Total 20
                Arguments.of(new int[] { 0, 6, 5, 2, 2, 5, 1, 9, 4 }, 1, 2, 20),
                // 4. Second Window Spans Before First Window Order Priority
                // Optimal strategy might require placing the 'secondLen' window physically
                // BEFORE the 'firstLen' window in the array indices.
                // Array:, firstLen = 3, secondLen = 2
                // Optimal picks: [3, 8] (len 2) and [8, 1, 9] (len 3) -> 11 + 18 = 29
                Arguments.of(new int[] { 3, 8, 1, 3, 2, 5, 4, 8, 1, 9 }, 3, 2, 29),
                // 5. Large Mixed Elements & Boundary Scenarios
                Arguments.of(new int[] { 1, 2, 10, 20, 1, 2, 3, 30, 40, 1 }, 2, 2, 100) // Picks [10, 20] and [30, 40]
                                                                                        // -> 30 + 70 = 100
        );
    }

    @ParameterizedTest(name = "Test {index}: Array={0}, firstLen={1}, secondLen={2} expected max sum={3}")
    @MethodSource("testData")
    void testFindMaxSum(int[] input, int firstLen, int secondLen, int expected) {
        Assertions.assertEquals(expected, MaxSumOfNotOverlappingSubarrays.findMaxSum(input, firstLen, secondLen));
    }
}
