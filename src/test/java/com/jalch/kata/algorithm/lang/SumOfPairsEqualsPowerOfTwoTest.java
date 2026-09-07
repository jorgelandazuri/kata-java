package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SumOfPairsEqualsPowerOfTwoTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Empty & Boundary Minimums
                Arguments.of(new int[] {}, 0),
                Arguments.of(new int[] { 1 }, 1), // Self-pair: 1 + 1 = 2 (2^1)
                Arguments.of(new int[] { 3 }, 0), // Self-pair: 3 + 3 = 6 (Not a power of 2)
                // 2. Simple Pairs (i < j)
                Arguments.of(new int[] { 3, 5 }, 1), // 3 + 5 = 8 (2^3)
                Arguments.of(new int[] { 1, 3 }, 2), // 1+1=2(2^1), 1+3=4(2^2), 3+3=6(X) -> 2 pairs total
                // 3. Multi-Duplicate Arrays (Testing frequency map multiplication logic)
                Arguments.of(new int[] { 2, 2, 2 }, 6),
                // Self-pairs: (0,0)->4, (1,1)->4, (2,2)->4 [3 pairs]
                // Cross-pairs: (0,1)->4, (0,2)->4, (1,2)->4 [3 pairs]
                // 4. Zero and Negative Scenarios (Ensuring <= 0 sums do not falsely trigger
                // bitwise filters)
                Arguments.of(new int[] { 0, 0 }, 0), // 0 + 0 = 0 (Not a power of 2)
                Arguments.of(new int[] { -2, -2 }, 0), // Negative sums cannot be powers of 2
                Arguments.of(new int[] { -1, 3 }, 1), // Cross-pair: -1 + 3 = 2 (2^1). Self-pairs: -1-1=-2(X), 3+3=6(X)
                // 5. Extreme Integer Boundary Thresholds (Testing 32-bit Integer Capacity
                // Constraints)
                Arguments.of(new int[] { Integer.MAX_VALUE, 1 }, 2),
                // Cross-pair: Integer.MAX_VALUE + 1 = 2^31 (Evaluates perfectly via 'long'
                // casts) and 1+1=2+2^1
                Arguments.of(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE }, 0),
                // Pure Math: 2 * (2^31 - 1) = 2^32 - 2 (Not a power of 2. Must not overflow
                // falsely to a match)
                // 6. Base-2 Floor Limit Edge Case
                Arguments.of(new int[] { 1, 0 }, 2) // 1+1=2(2^1), 1+0=1(2^0), 0+0=0(X) -> 2 pairs total
        );
    }

    @ParameterizedTest(name = "Test {index}: for array {0}, expected pairs = {1}")
    @MethodSource("testData")
    void testFindAndFindOptimised(int[] nums, int expectedPairs) {
        // Validate basic O(N^2) bitwise strategy
        Assertions.assertEquals(expectedPairs, SumOfPairsEqualsPowerOfTwo.find(nums));
        // Validate the optimized O(N) Hash Map strategy
        Assertions.assertEquals(expectedPairs, SumOfPairsEqualsPowerOfTwo.findOptmised(nums));
    }
}
