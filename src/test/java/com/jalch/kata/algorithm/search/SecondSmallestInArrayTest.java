package com.jalch.kata.algorithm.search;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.*;

public class SecondSmallestInArrayTest {

    @Test
    public void null_input() {
        assertEquals(MAX_VALUE, SecondSmallestInArray.find(null));
    }

    @Test
    public void empty() {
        int[] input = {};
        assertEquals(MAX_VALUE, SecondSmallestInArray.find(input));
    }

    @Test
    public void single_element() {
        int[] input = {0};
        assertEquals(0, SecondSmallestInArray.find(input));
    }

    @Test
    public void two_elements_ordered() {
        int[] input = {0,1};
        assertEquals(1, SecondSmallestInArray.find(input));
    }

    @Test
    public void many_elements_unordered() {
        int[] input = {-3,0,1,345, MIN_VALUE, 5,-334,0};
        assertEquals(-334, SecondSmallestInArray.find(input));
    }

    @Test
    public void many_elements_all_same() {
        int[] input = {-3,-3,-3,-3,-3,-3,-3,-3};
        assertEquals(-3, SecondSmallestInArray.find(input));
    }

    @Test
    public void many_elements_max_and_min() {
        int[] input = {MAX_VALUE, MIN_VALUE};
        assertEquals(MAX_VALUE, SecondSmallestInArray.find(input));
    }
}