package com.jalch.kata.algorithm.lang;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

public class MaxProfitToTradeAStockTest {

    private static int[][] pricesList = {
            {},
            { 1 },
            { 1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9 },
            { 8, 2, 6, 4, 7, 5 },
            { 7, 6, 4, 3, 1 },
            { 2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8 },
            { 1, 2 }
    };

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(pricesList[0], 0),
                Arguments.of(pricesList[1], 0),
                Arguments.of(pricesList[2], 9),
                Arguments.of(pricesList[3], 5),
                Arguments.of(pricesList[4], 0),
                Arguments.of(pricesList[5], 7),
                Arguments.of(pricesList[6], 1));
    }

    @ParameterizedTest(name = "For price streams {0}, the max profit should be {1}")
    @MethodSource("testData")
    public void maxProfit(int[] input, int expectedMaxProfit) {
        Assertions.assertEquals(expectedMaxProfit, MaxProfitToTradeAStock.getWithOnSquared(input));
        Assertions.assertEquals(expectedMaxProfit, MaxProfitToTradeAStock.getWithOn(input));
    }

}
