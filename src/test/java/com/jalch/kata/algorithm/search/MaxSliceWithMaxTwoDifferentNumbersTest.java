package com.jalch.kata.algorithm.search;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class MaxSliceWithMaxTwoDifferentNumbersTest {

    @Test
    public void null_input() {
        assertEquals(0L, MaxSliceWithMaxTwoDifferentNumbers.calculate(null));
    }

    @Test
    public void empty_input() {
        assertEquals(0L, MaxSliceWithMaxTwoDifferentNumbers.calculate(emptyList()));
    }

    @Test
    public void single_element() {
        assertEquals(1L, MaxSliceWithMaxTwoDifferentNumbers.calculate(singletonList(1)));
    }

    @Test
    public void multiple_elements_all_same() {
        assertEquals(2L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1)));
        assertEquals(6L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1,1,1,1,1)));
        assertEquals(3L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(-2,-2,-2)));
        assertEquals(9L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(2,2,2,2,2,2,2,2,2)));
    }

    @Test
    public void multiple_elements_if_same_are_consecutive() {
        assertEquals(5L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1,2,1,2,3,3,3,3)));
        assertEquals(2L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2)));
        assertEquals(5L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1,1,2,2,3)));
        assertEquals(3L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(3,1,2,2)));
        assertEquals(5L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2,2,2,4,4,1,2,2)));
        assertEquals(3L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1,3)));
        assertEquals(4L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,1,3,3)));
        assertEquals(5L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(2,1,1,1,2)));
        assertEquals(6L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2,1,1,1,2)));
        assertEquals(3L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2,4,4,1,2,2)));
        assertEquals(3L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2,3,4,5,1,4,1,2,4)));
        assertEquals(9L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(2,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6)));
        assertEquals(7L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(2,2,2,3,2,3,2)));
        assertEquals(8L, MaxSliceWithMaxTwoDifferentNumbers.calculate(asList(1,2,1,2,3,1,1,2,3,2,2,2,2,3,0,1,1,1,1,0,0,0)));
    }
}