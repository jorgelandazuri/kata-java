package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MinimumContiguousSubarrayOverridingTargetTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, Target is 0, or Sum is completely impossible)
                Arguments.of(null, 7, 0),
                Arguments.of(new int[] {}, 7, 0),
                Arguments.of(new int[] { 1, 2, 3 }, 0, 0),
                Arguments.of(new int[] { 1, 2, 4 }, 10, 0),
                // 2. Exact Single-Element Matches (Minimal possible window length = 1)
                Arguments.of(new int[] { 7 }, 7, 1),
                Arguments.of(new int[] { 2, 3, 1, 2, 4, 3 }, 4, 1),
                // 3. Whole Array Requirements
                Arguments.of(new int[] { 1, 1, 1, 1 }, 4, 4),
                // 4. Dynamic Window Shrinking & Shifting
                Arguments.of(new int[] { 2, 3, 1, 2, 4, 3 }, 7, 2),
                // 5. Large Value Gaps
                Arguments.of(new int[] { 1, 100, 3, 4 }, 100, 1),
                // 6. Advanced Edge Cases (Overshooting & Direct End-Matches)
                // Overshoots dramatically at the very beginning (Length 1)
                Arguments.of(new int[] { 1000, 1, 1, 1 }, 7, 1),
                // Element satisfying target hits at the very last index (Length 1)
                Arguments.of(new int[] { 1, 2, 3, 10 }, 10, 1),
                // [5, 2, 8] -> shrink left -> [2, 8] hits target exactly (Length 2)
                Arguments.of(new int[] { 2, 1, 5, 2, 8 }, 10, 2),
                // Sum of entire array (15) is exactly 1 short of target (16)
                Arguments.of(new int[] { 1, 2, 3, 4, 5 }, 16, 0));
    }

    @ParameterizedTest(name = "Test {index}: Array={0}, Target={1} expected minimum length={2}")
    @MethodSource("testData")
    void testGet(int[] input, int target, int expected) {
        Assertions.assertEquals(expected, MinimumContiguousSubarrayOverridingTarget.get(input, target));
    }
}
