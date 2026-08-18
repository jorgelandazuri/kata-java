package com.jalch.kata.algorithm.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ReachValueWithMinimumNumberOfCoinsWithGivenDenominationsTest {

    private static final int ANY_VALUE = 2678236;
    private static final List<Integer> NOT_FOUND_VALUES_RESPONSE = Collections.singletonList(0);
    private static final List<Integer> ANY_LIST = Arrays.asList(8, 4, 3, 231);

    @Test
    public void null_list() {
        List<Integer> result = ReachValueWithMinimumNumberOfCoinsWithGivenDenominations.getCoins(null, ANY_VALUE);
        assertEquals(NOT_FOUND_VALUES_RESPONSE.get(0), result.get(0));
    }

    @Test
    public void empty_list() {
        List<Integer> result = ReachValueWithMinimumNumberOfCoinsWithGivenDenominations.getCoins(new ArrayList<>(), ANY_VALUE);
        assertEquals(NOT_FOUND_VALUES_RESPONSE.get(0), result.get(0));
    }

    @Test
    public void negative_value() {
        List<Integer> result = ReachValueWithMinimumNumberOfCoinsWithGivenDenominations.getCoins(ANY_LIST, -1);
        assertEquals(NOT_FOUND_VALUES_RESPONSE.get(0), result.get(0));
    }

    @Test
    public void zero_value() {
        List<Integer> result = ReachValueWithMinimumNumberOfCoinsWithGivenDenominations.getCoins(ANY_LIST, 0);
        assertEquals(NOT_FOUND_VALUES_RESPONSE.get(0), result.get(0));
    }

    @Test
    public void valid_enough_denominations_and_values() {
        assertExpectedDenominationsForValue(3, Arrays.asList(2,1));
        assertExpectedDenominationsForValue(4, Arrays.asList(2,2));
        assertExpectedDenominationsForValue(5, Arrays.asList(5));
        assertExpectedDenominationsForValue(6, Arrays.asList(5,1));
        assertExpectedDenominationsForValue(8, Arrays.asList(5,2,1));
        assertExpectedDenominationsForValue(10, Arrays.asList(10));
        assertExpectedDenominationsForValue(154, Arrays.asList(100,50,2,2));
        assertExpectedDenominationsForValue(154, Arrays.asList(100,50,2,2));
    }

    private void assertExpectedDenominationsForValue(int value, List<Integer> expected) {
        List<Integer> denominations = Arrays.asList(5,50,5,100,1,20,2,10,27,1);
        List<Integer> result = ReachValueWithMinimumNumberOfCoinsWithGivenDenominations.getCoins(denominations, value);
        assertEquals(expected, result);
    }
}