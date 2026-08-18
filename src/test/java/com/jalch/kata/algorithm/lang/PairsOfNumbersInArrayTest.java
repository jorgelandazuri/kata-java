package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PairsOfNumbersInArrayTest {

    @Test
    public void empty_input() {
        assertEquals(0, PairsOfNumbersInArray.get(new int[]{}));
    }

    @Test
    public void single_element_no_pairs() {
        assertEquals(0, PairsOfNumbersInArray.get(new int[]{2}));
    }

    @Test
    public void single_pair() {
        assertEquals(1, PairsOfNumbersInArray.get(new int[]{2,2}));
    }

    @Test
    public void no_pairs() {
        assertEquals(0, PairsOfNumbersInArray.get(new int[]{1,2,3,4,5}));
    }

    @Test
    public void multiple_pairs_hacker_rank_test_case() {
        assertEquals(3, PairsOfNumbersInArray.get(new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20}));
    }
}