package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MatrixPerimeterFrameShiftTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, and Negative / Zero Shifts)
                Arguments.of(null, 3, null),
                Arguments.of(new int[][] {}, 2, new int[][] {}),
                Arguments.of(new int[][] { { 1, 2 }, { 3, 4 } }, 0, new int[][] { { 1, 2 }, { 3, 4 } }),
                Arguments.of(new int[][] { { 1, 2 }, { 3, 4 } }, -1, new int[][] { { 1, 2 }, { 3, 4 } }),
                // 2. Minimum Boundary Grid (2x2 Matrix - 4 perimeter elements)
                // Perimeter clockwise sequence: 1 -> 2 -> 4 -> 3
                // Shifting 1 step clockwise makes: 3 moves to top-left, 1 to top-right, 2 to
                // bottom-right, 4 to bottom-left
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }, 1, new int[][] {
                        { 3, 1 },
                        { 4, 2 }
                }),
                // 3. Modulo Loop Truncation (Shift exceeds or matches perimeter capacity)
                // Perimeter size for 2x2 is 4 elements. Shifting by 4 leaves it unchanged.
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }, 4, new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }),
                // Shifting by 5 on a perimeter of 4 is identical to a clean 1-step shift.
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }, 5, new int[][] {
                        { 3, 1 },
                        { 4, 2 }
                }),
                // 4. Square Matrix with Internal Anchor Verification (3x3 Grid)
                // Perimeter shifts, but center '5' must stay perfectly anchored at index (1,1)
                // Perimeter stream clockwise: 1, 2, 3, 6, 9, 8, 7, 4 (8 elements total)
                // Shifting 2 steps clockwise shifts everything by two positions along the
                // border.
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }, 2, new int[][] {
                        { 7, 4, 1 },
                        { 8, 5, 2 },
                        { 9, 6, 3 }
                }),
                // 5. Asymmetric Rectangular Matrices (Varying row/column bounds)
                // 2 Rows x 4 Columns matrix (Perimeter size: 2*2 + 2*2 = 8 elements)
                // Clockwise: 1 -> 2 -> 3 -> 4 -> 8 -> 7 -> 6 -> 5
                // Shifting 1 step clockwise.
                Arguments.of(new int[][] {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 }
                }, 1, new int[][] {
                        { 5, 1, 2, 3 },
                        { 6, 7, 8, 4 }
                }));
    }

    @ParameterizedTest(name = "Test {index}: For matrix {0} frame {1} shift result should be {2}")
    @MethodSource("testData")
    void testShift(int[][] input, int k, int[][] expected) {
        MatrixPerimeterFrameShift.shift(input, k);
        Assertions.assertArrayEquals(expected, input);
    }
}
