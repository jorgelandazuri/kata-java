package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SameCharactersRepetitionsExceptAsManyAsOneTest {

    private SameCharactersRepetitionsExceptAsManyAsOne underTest = new SameCharactersRepetitionsExceptAsManyAsOne();

    @Test
    public void null_input() {
        assertEquals("NO", underTest.valid(null));
    }

    @Test
    public void empty_input() {
        assertEquals("NO", underTest.valid(""));
    }

    @Test
    public void not_between_a_to_z() {
        assertEquals("NO", underTest.valid("1"));
        assertEquals("NO", underTest.valid("_"));
        assertEquals("NO", underTest.valid("2131234"));
        assertEquals("NO", underTest.valid("*"));
        assertEquals("NO", underTest.valid("A"));
        assertEquals("NO", underTest.valid("P"));
        assertEquals("NO", underTest.valid("~"));
    }

    @Test
    public void single_valid_character() {
        assertEquals("YES", underTest.valid("a"));
        assertEquals("YES", underTest.valid("b"));
        assertEquals("YES", underTest.valid("d"));
        assertEquals("YES", underTest.valid("f"));
        assertEquals("YES", underTest.valid("j"));
        assertEquals("YES", underTest.valid("n"));
        assertEquals("YES", underTest.valid("t"));
        assertEquals("YES", underTest.valid("z"));
    }

    @Test
    public void same_repetitions() {
        assertEquals("YES", underTest.valid("aa"));
        assertEquals("YES", underTest.valid("cababc"));
        assertEquals("YES", underTest.valid("abc"));
    }

    @Test
    public void not_same_repetitions() {
        assertEquals("NO", underTest.valid("aabxgb"));
        assertEquals("NO", underTest.valid("ababdc"));
        assertEquals("NO", underTest.valid("ccabc"));
    }

    @Test
    public void same_repetitions_but_one() {
        assertEquals("YES", underTest.valid("aac"));
        assertEquals("YES", underTest.valid("cababc"));
        assertEquals("YES", underTest.valid("abc"));
        //AA BB CC DD EEE FF GG HH
        assertEquals("YES", underTest.valid("abcdefghhgfedecba"));
    }
}