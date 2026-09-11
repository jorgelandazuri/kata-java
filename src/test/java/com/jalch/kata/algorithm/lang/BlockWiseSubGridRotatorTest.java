package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BlockWiseSubGridRotatorTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null and Empty)
                Arguments.of(null, null),
                Arguments.of(new int[][] {}, new int[][] {}),
                // 2. Minimum Valid Sub-Grid (Exactly one 2x2 block)
                // [1, 2] rotating clockwise [3, 1]
                // [3, 4] becomes -> [4, 2]
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }, new int[][] {
                        { 3, 1 },
                        { 4, 2 }
                }),
                // 3. Standard 4x4 Grid (Four separate 2x2 blocks rotating independently)
                // Block TL: [1,2]/[5,6] -> [5,1]/[6,2] | Block TR: [3,4]/[7,8] -> [7,3]/[8,4]
                // Block BL: [9,10]/[13,14] -> [13,9]/[14,10] | Block BR: [11,12]/[15,16] ->
                // [15,11]/[16,12]
                Arguments.of(new int[][] {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }
                }, new int[][] {
                        { 5, 1, 7, 3 },
                        { 6, 2, 8, 4 },
                        { 13, 9, 15, 11 },
                        { 14, 10, 16, 12 }
                }),
                // 4. Repeated Values and Homogeneous Sub-Grids
                // Blocks with identical items should look completely unchanged after rotation
                Arguments.of(new int[][] {
                        { 5, 5, 1, 2 },
                        { 5, 5, 3, 4 },
                        { 9, 9, 0, 0 },
                        { 9, 9, 0, 0 }
                }, new int[][] {
                        { 5, 5, 3, 1 },
                        { 5, 5, 4, 2 },
                        { 9, 9, 0, 0 },
                        { 9, 9, 0, 0 }
                }));
    }

    @ParameterizedTest(name = "Test {index}: Matrix  {0} 2x2 block-wise in-place rotation should be {1}")
    @MethodSource("testData")
    void testRotate(int[][] input, int[][] expected) {
        // Run the function to mutate the array reference in-place
        BlockWiseSubGridRotator.rotate(input);
        // Assert that the mutated input reference matches the expected matrix layout
        Assertions.assertArrayEquals(expected, input);
    }
}
