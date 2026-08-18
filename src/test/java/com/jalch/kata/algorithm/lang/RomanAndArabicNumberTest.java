package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RomanAndArabicNumberTest {

    @Test
    public void null_string() {
        assertEquals("", RomanAndArabicNumber.convert(null));
    }

    @Test
    public void empty_string() {
        assertEquals("", RomanAndArabicNumber.convert(""));
    }

    @Test
    public void invalid_or_negative_or_zero() {
        assertEquals("", RomanAndArabicNumber.convert("--345"));
        assertEquals("", RomanAndArabicNumber.convert("-345"));
        assertEquals("", RomanAndArabicNumber.convert("-1"));
        assertEquals("", RomanAndArabicNumber.convert("0"));
    }

    @Test
    public void invalid_roman() {
        assertEquals("", RomanAndArabicNumber.convert("a"));
        assertEquals("", RomanAndArabicNumber.convert("_I"));
        assertEquals("", RomanAndArabicNumber.convert("*"));
        assertEquals("", RomanAndArabicNumber.convert("IIIV"));
        assertEquals("", RomanAndArabicNumber.convert("IIIX"));
        assertEquals("", RomanAndArabicNumber.convert("-I"));
    }

    @Test
    public void valid_arabic_to_roman() {
        assertEquals("I", RomanAndArabicNumber.convert("1"));
        assertEquals("II", RomanAndArabicNumber.convert("2"));
        assertEquals("III", RomanAndArabicNumber.convert("3"));
        assertEquals("IV", RomanAndArabicNumber.convert("4"));
        assertEquals("V", RomanAndArabicNumber.convert("5"));
        assertEquals("VI", RomanAndArabicNumber.convert("6"));
        assertEquals("VII", RomanAndArabicNumber.convert("7"));
        assertEquals("VIII", RomanAndArabicNumber.convert("8"));
        assertEquals("IX", RomanAndArabicNumber.convert("9"));
        assertEquals("X", RomanAndArabicNumber.convert("10"));
        assertEquals("XI", RomanAndArabicNumber.convert("11"));
        assertEquals("XIV", RomanAndArabicNumber.convert("14"));
        assertEquals("XIX", RomanAndArabicNumber.convert("19"));
        assertEquals("XXXIX", RomanAndArabicNumber.convert("39"));
        assertEquals("XL", RomanAndArabicNumber.convert("40"));
        assertEquals("L", RomanAndArabicNumber.convert("50"));
        assertEquals("LXXIV", RomanAndArabicNumber.convert("74"));
        assertEquals("XC", RomanAndArabicNumber.convert("90"));
        assertEquals("XCVII", RomanAndArabicNumber.convert("97"));
        assertEquals("XCIX", RomanAndArabicNumber.convert("99"));
        assertEquals("C", RomanAndArabicNumber.convert("100"));
        assertEquals("CCCXXIX", RomanAndArabicNumber.convert("329"));
        assertEquals("CDLXXXVII", RomanAndArabicNumber.convert("487"));
        assertEquals("CDXC", RomanAndArabicNumber.convert("490"));
        assertEquals("D", RomanAndArabicNumber.convert("500"));
        assertEquals("DCXVII", RomanAndArabicNumber.convert("617"));
        assertEquals("CMI", RomanAndArabicNumber.convert("901"));
        assertEquals("CMXLIX", RomanAndArabicNumber.convert("949"));
        assertEquals("CMXCIX", RomanAndArabicNumber.convert("999"));
        assertEquals("MMMCMXCIX", RomanAndArabicNumber.convert("3999"));
        assertEquals("MMMM", RomanAndArabicNumber.convert("4000"));
        assertEquals("MMMMCM", RomanAndArabicNumber.convert("4900"));
        assertEquals("MMMMCMXCIX", RomanAndArabicNumber.convert("4999"));
    }

    @Test
    public void valid_roman_to_arabic() {
        assertEquals("1", RomanAndArabicNumber.convert("I"));
        assertEquals("2", RomanAndArabicNumber.convert("II"));
        assertEquals("3", RomanAndArabicNumber.convert("III"));
        assertEquals("4", RomanAndArabicNumber.convert("IV"));
        assertEquals("5", RomanAndArabicNumber.convert("V"));
        assertEquals("6", RomanAndArabicNumber.convert("VI"));
        assertEquals("7", RomanAndArabicNumber.convert("VII"));
        assertEquals("8", RomanAndArabicNumber.convert("VIII"));
        assertEquals("9", RomanAndArabicNumber.convert("IX"));
        assertEquals("10", RomanAndArabicNumber.convert("X"));
        assertEquals("11", RomanAndArabicNumber.convert("XI"));
        assertEquals("14", RomanAndArabicNumber.convert("XIV"));
        assertEquals("19", RomanAndArabicNumber.convert("XIX"));
        assertEquals("39", RomanAndArabicNumber.convert("XXXIX"));
        assertEquals("40", RomanAndArabicNumber.convert("XL"));
        assertEquals("50", RomanAndArabicNumber.convert("L"));
        assertEquals("74", RomanAndArabicNumber.convert("LXXIV"));
        assertEquals("90", RomanAndArabicNumber.convert("XC"));
        assertEquals("97", RomanAndArabicNumber.convert("XCVII"));
        assertEquals("99", RomanAndArabicNumber.convert("XCIX"));
        assertEquals("100", RomanAndArabicNumber.convert("C"));
        assertEquals("329", RomanAndArabicNumber.convert("CCCXXIX"));
        assertEquals("487", RomanAndArabicNumber.convert("CDLXXXVII"));
        assertEquals("490", RomanAndArabicNumber.convert("CDXC"));
        assertEquals("500", RomanAndArabicNumber.convert("D"));
        assertEquals("617", RomanAndArabicNumber.convert("DCXVII"));
        assertEquals("901", RomanAndArabicNumber.convert("CMI"));
        assertEquals("949", RomanAndArabicNumber.convert("CMXLIX"));
        assertEquals("999", RomanAndArabicNumber.convert("CMXCIX"));
        assertEquals("3999", RomanAndArabicNumber.convert("MMMCMXCIX"));
        assertEquals("4000", RomanAndArabicNumber.convert("MMMM"));
        assertEquals("4900", RomanAndArabicNumber.convert("MMMMCM"));
        assertEquals("4999", RomanAndArabicNumber.convert("MMMMCMXCIX"));
    }
}