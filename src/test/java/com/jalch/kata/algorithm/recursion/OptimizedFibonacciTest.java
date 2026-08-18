package com.jalch.kata.algorithm.recursion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class OptimizedFibonacciTest {

    private OptimizedFibonacci underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptimizedFibonacci();
    }

    @Test
    public void negative() {
        assertThrows(IllegalArgumentException.class, () -> underTest.calculate(-1, new long[1]));
        assertThrows(IllegalArgumentException.class, () -> underTest.calculate(-9, new long[1]));
        assertThrows(IllegalArgumentException.class, () -> underTest.calculate(-1000000, new long[1]));
    }

    @Test
    public void zero() throws IllegalArgumentException {
        assertEquals(0L, underTest.calculate(0, new long[1]));
    }

    @Test
    public void one() throws IllegalArgumentException {
        assertEquals(1L, underTest.calculate(1, new long[1]));
    }

    @Test
    public void two_or_more() {
        assertEquals(1L, underTest.calculate(2, new long[11]));
        assertEquals(2L, underTest.calculate(3, new long[11]));
        assertEquals(3L, underTest.calculate(4, new long[11]));
        assertEquals(5L, underTest.calculate(5, new long[11]));
        assertEquals(8L, underTest.calculate(6, new long[11]));
        assertEquals(13L, underTest.calculate(7, new long[11]));
        assertEquals(21L, underTest.calculate(8, new long[11]));
        assertEquals(34L, underTest.calculate(9, new long[11]));
        assertEquals(55L, underTest.calculate(10, new long[11]));
        assertEquals(1_836_311_903L, underTest.calculate(46, new long[47]));
        assertEquals(99_194_853_094_755_497L, underTest.calculate(83, new long[84]));
    }

}
