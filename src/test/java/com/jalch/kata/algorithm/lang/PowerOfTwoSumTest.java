package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerOfTwoSumTest {


    @Test
    public void sum_pow_of_negative() {
        assertEquals(0L, PowerOfTwoSum.calculateWithShifting(-1));
    }

    @Test
    public void sum_pow_of_zero() {
        assertEquals(0L, PowerOfTwoSum.calculateWithShifting(0));
    }

    @Test
    public void sum_pow_of_positive_with_shifting() {
        assertEquals(1L, PowerOfTwoSum.calculateWithShifting(1));
        assertEquals(3L, PowerOfTwoSum.calculateWithShifting(2));
        assertEquals(7L, PowerOfTwoSum.calculateWithShifting(3));
        assertEquals(15L, PowerOfTwoSum.calculateWithShifting(4));
        assertEquals(31L, PowerOfTwoSum.calculateWithShifting(5));
        assertEquals(63L, PowerOfTwoSum.calculateWithShifting(6));
        assertEquals(127L, PowerOfTwoSum.calculateWithShifting(7));
        assertEquals(255L, PowerOfTwoSum.calculateWithShifting(8));
        assertEquals(511L, PowerOfTwoSum.calculateWithShifting(9));
        assertEquals(1023L, PowerOfTwoSum.calculateWithShifting(10));
    }

    @Test
    public void sum_pow_of_hundred_with_shifting() {
        assertEquals(Long.MAX_VALUE, PowerOfTwoSum.calculateWithShifting(63));
    }

    @Test
    public void sum_pow_of_positive_with_math_pow() {
        assertEquals(1L, PowerOfTwoSum.calculateWithPow(1));
        assertEquals(3L, PowerOfTwoSum.calculateWithPow(2));
        assertEquals(7L, PowerOfTwoSum.calculateWithPow(3));
        assertEquals(15L, PowerOfTwoSum.calculateWithPow(4));
        assertEquals(31L, PowerOfTwoSum.calculateWithPow(5));
        assertEquals(63L, PowerOfTwoSum.calculateWithPow(6));
        assertEquals(127L, PowerOfTwoSum.calculateWithPow(7));
        assertEquals(255L, PowerOfTwoSum.calculateWithPow(8));
        assertEquals(511L, PowerOfTwoSum.calculateWithPow(9));
        assertEquals(1023L, PowerOfTwoSum.calculateWithPow(10));
    }

    @Test
    public void sum_pow_of_hundred_with_math_pow() {
        assertEquals(Long.MAX_VALUE, PowerOfTwoSum.calculateWithPow(63));
    }
}