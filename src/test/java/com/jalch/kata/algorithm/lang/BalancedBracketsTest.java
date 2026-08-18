package com.jalch.kata.algorithm.lang;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static com.jalch.kata.algorithm.lang.BalancedBrackets.isBalanced;


public class BalancedBracketsTest {

    @Test
    public void null_input() {
        assertFalse(isBalanced(null));
    }

    @Test
    public void empty_input() {
        assertTrue(isBalanced(""));
    }

    @Test
    public void not_opening_or_closing_chars_in_input() {
        assertTrue(isBalanced("adkhasljd732"));
    }

    @Test
    public void single_opening_char_input() {
        assertFalse(isBalanced("("));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("["));
    }

    @Test
    public void two_or_more_opening_char_input() {
        assertFalse(isBalanced("(das(hj("));
        assertFalse(isBalanced("{hsajd{hasd{"));
        assertFalse(isBalanced("[[a["));
        assertFalse(isBalanced("(({hsajd{hasd[{0["));
    }

    @Test
    public void starts_with_closing() {
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("}shdjkas{}"));
        assertFalse(isBalanced("][[a["));
    }

    @Test
    public void single_open_and_close_pair() {
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
    }

    @Test
    public void valid_nested_open_and_close_pair() {
        assertTrue(isBalanced("(())"));
        assertTrue(isBalanced("{{}}"));
        assertTrue(isBalanced("[[]]"));
        assertTrue(isBalanced("231((fvd3))fd"));
        assertTrue(isBalanced("{as-d{}}"));
        assertTrue(isBalanced("_a[[sadasd];]"));
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("{{[[(())]]}}"));
    }

    @Test
    public void not_valid_nested_open_and_close_pair() {
        assertFalse(isBalanced("(()))"));
        assertFalse(isBalanced("{{{}}"));
        assertFalse(isBalanced("[[]]]"));
        assertFalse(isBalanced("(231((fvd3))fd"));
        assertFalse(isBalanced("{as-d{}}}"));
        assertFalse(isBalanced("_[a[[sadasd];]"));
        assertFalse(isBalanced("{([()]}}"));
        assertFalse(isBalanced("{{[[(())])]}}"));
    }

}
