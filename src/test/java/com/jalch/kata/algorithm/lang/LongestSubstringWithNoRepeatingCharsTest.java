package com.jalch.kata.algorithm.lang;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;


public class LongestSubstringWithNoRepeatingCharsTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("abcabcbb", 3),
            Arguments.of("pwwkew", 3),
            Arguments.of("bbbbb", 1),
            Arguments.of("ababababa", 2),
            Arguments.of("a", 1),
            Arguments.of("", 0),
            Arguments.of(null, 0),
            Arguments.of("ABCDEFGHI", 9 ),
            Arguments.of("ABCDEDCBA", 5),
            Arguments.of("AAAABBBBCCCCDDDD", 2)
        );
    }

    @ParameterizedTest(name = "For string {0} the expected longest substring is {1}")
    @MethodSource("testData")
    public void longestSubstring(String input, int expected) {
        Assertions.assertEquals(expected, LongestSubstringWithNoRepeatingChars.find(input));
        Assertions.assertEquals(expected, LongestSubstringWithNoRepeatingChars.findWithSolutionFromEducative(input));
    }  

}
