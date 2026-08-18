package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringToIntegerTest {

    @Test
    public void null_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert(null));
    }

    @Test
    public void empty_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert(""));
    }

    @Test
    public void zero() {
        assertEquals(0, StringToInteger.convert("0"));
    }

    @Test
    public void single_digit_positive_number() {
        assertEquals(1, StringToInteger.convert("1"));
        assertEquals(2, StringToInteger.convert("2"));
        assertEquals(3, StringToInteger.convert("3"));
        assertEquals(4, StringToInteger.convert("4"));
        assertEquals(5, StringToInteger.convert("5"));
        assertEquals(6, StringToInteger.convert("6"));
        assertEquals(7, StringToInteger.convert("7"));
        assertEquals(8, StringToInteger.convert("8"));
        assertEquals(9, StringToInteger.convert("9"));
    }

    @Test
    public void single_digit_negative_number() {
        assertEquals(-1, StringToInteger.convert("-1"));
        assertEquals(-2, StringToInteger.convert("-2"));
        assertEquals(-3, StringToInteger.convert("-3"));
        assertEquals(-4, StringToInteger.convert("-4"));
        assertEquals(-5, StringToInteger.convert("-5"));
        assertEquals(-6, StringToInteger.convert("-6"));
        assertEquals(-7, StringToInteger.convert("-7"));
        assertEquals(-8, StringToInteger.convert("-8"));
        assertEquals(-9, StringToInteger.convert("-9"));
    }

    @Test
    public void multiple_digit_positive_numbers() {
        assertEquals(12, StringToInteger.convert("12"));
        assertEquals(2134, StringToInteger.convert("2134"));
        assertEquals(9992, StringToInteger.convert("9992"));
        assertEquals(Integer.MAX_VALUE, StringToInteger.convert(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void multiple_digit_negative_numbers() {
        assertEquals(-12, StringToInteger.convert("-12"));
        assertEquals(-2134, StringToInteger.convert("-2134"));
        assertEquals(-9992, StringToInteger.convert("-9992"));
        assertEquals(Integer.MIN_VALUE, StringToInteger.convert(String.valueOf(Integer.MIN_VALUE)));
    }

    @Test
    public void single_dash() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("-"));
    }

    @Test
    public void negative_and_letters_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("-12asf"));
    }

    @Test
    public void number_middle_dash_and_letters_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("12-asf"));
    }

    @Test
    public void underscore_number_and_letters_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("_1f"));
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("    "));
    }

    @Test
    public void spaces_input() {
        assertThrows(IllegalArgumentException.class, () -> StringToInteger.convert("       "));
    }
}
