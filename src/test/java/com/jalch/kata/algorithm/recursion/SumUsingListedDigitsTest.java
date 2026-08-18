package com.jalch.kata.algorithm.recursion;

import org.junit.jupiter.api.Test;

import com.jalch.kata.algorithm.recursion.SumUsingListedDigits;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.asList;

public class SumUsingListedDigitsTest {

    private static final LinkedList<Integer> ANY_LIST = new LinkedList<>(asList(1, 2, 3));
    private static final LinkedList<Integer> EMPTY_LIST = new LinkedList<>();


    private SumUsingListedDigits underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SumUsingListedDigits();
    }

    @Test
    public void invalid_input() {
        assertEquals(EMPTY_LIST, underTest.sumOf(null, null));
        assertEquals(ANY_LIST, underTest.sumOf(null, ANY_LIST));
        assertEquals(ANY_LIST, underTest.sumOf(ANY_LIST, null));
        assertEquals(EMPTY_LIST, underTest.sumOf(null, EMPTY_LIST));
        assertEquals(EMPTY_LIST, underTest.sumOf(EMPTY_LIST, null));
        assertEquals(EMPTY_LIST, underTest.sumOf(EMPTY_LIST, EMPTY_LIST));
    }

    @Test
    public void sum_of_both_zero() {
        assertEquals(aListOf(0), underTest.sumOf(aListOf(0), aListOf(0)));
    }

    @Test
    public void sum_of_one_zero() {
        assertEquals(aListOf(1,2,3), underTest.sumOf(aListOf(0), aListOf(1,2,3)));
        assertEquals(aListOf(1,2,3), underTest.sumOf(aListOf(1,2,3), aListOf(0)));
    }

    @Test
    public void single_digits_lists() {
        assertEquals(aListOf(4), underTest.sumOf(aListOf(2), aListOf(2)));
    }

    @Test
    public void same_sized_list() {
        assertEquals(aListOf(2,4,6), underTest.sumOf(aListOf(1,2,3), aListOf(1,2,3)));
        assertEquals(aListOf(3,6), underTest.sumOf(aListOf(1,8), aListOf(1,8)));
        assertEquals(aListOf(1,9,8), underTest.sumOf(aListOf(9,9), aListOf(9,9)));
        assertEquals(aListOf(3,0,1,8), underTest.sumOf(aListOf(2,0,0,9), aListOf(1,0,0,9)));
    }

    @Test
    public void different_sized_list() {
        assertEquals(aListOf(7,8,0,7), underTest.sumOf(aListOf(7,2,4,3), aListOf(5,6,4)));
        assertEquals(aListOf(2,6), underTest.sumOf(aListOf(1,8), aListOf(8)));
        assertEquals(aListOf(1,0,8), underTest.sumOf(aListOf(9,9), aListOf(9)));
        assertEquals(aListOf(2,1,0,1,8), underTest.sumOf(aListOf(1,2,0,0,9), aListOf(9,0,0,9)));
    }

    private LinkedList<Integer> aListOf(Integer ... integers) {
        return new LinkedList<>(Arrays.asList(integers));
    }
}