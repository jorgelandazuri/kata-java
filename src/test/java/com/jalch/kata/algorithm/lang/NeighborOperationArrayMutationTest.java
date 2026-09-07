package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Assertions;

public class NeighborOperationArrayMutationTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new int[] {}, NeighborOperationArrayMutation.NeighborIntOperation.ADD, new int[] {}),
                Arguments.of(new int[] { 1 }, NeighborOperationArrayMutation.NeighborIntOperation.ADD, new int[] { 1 }),
                Arguments.of(new int[] {}, NeighborOperationArrayMutation.NeighborIntOperation.MULTIPLY, new int[] {}),
                Arguments.of(new int[] { 1 }, NeighborOperationArrayMutation.NeighborIntOperation.MULTIPLY,
                        new int[] { 1 }),
                Arguments.of(new int[] { 1, 2, 3, 4, 5 }, NeighborOperationArrayMutation.NeighborIntOperation.ADD,
                        new int[] { 3, 6, 9, 12, 9 }),
                Arguments.of(new int[] { 1, 2, 3, 4, 5 }, NeighborOperationArrayMutation.NeighborIntOperation.MULTIPLY,
                        new int[] { 2, 6, 24, 60, 20 }));
    }

    @ParameterizedTest(name = "Test {index}: nums = {0}, op = {1}")
    @MethodSource("testData")
    void testMutate(int[] nums, NeighborOperationArrayMutation.NeighborIntOperation op, int[] expected) {
        Assertions.assertArrayEquals(expected, NeighborOperationArrayMutation.mutateUsingExtraArray(nums, op));
        Assertions.assertArrayEquals(expected, NeighborOperationArrayMutation.mutateInPlace(nums, op));
    }
}
