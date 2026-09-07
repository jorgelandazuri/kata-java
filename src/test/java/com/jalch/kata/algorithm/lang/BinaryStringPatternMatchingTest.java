package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryStringPatternMatchingTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // Format: Arguments.of(source, pattern, expectedMatches)
                Arguments.of("aab", "001", 1),
                Arguments.of("amazing", "010", 2),
                Arguments.of("aaaa", "00", 3),
                Arguments.of("banana", "1010", 2),
                Arguments.of("yoyoyo", "010", 0),
                Arguments.of("yoyoyo", "00", 5),
                Arguments.of("bcdfgh", "111", 4),
                // High-Match & Extreme Repetition Scenarios
                Arguments.of("bbbbbbbbbb", "1", 10),
                // Alternating vowels and consonants -> Matches "ab", "ba", "ab", "ba", "ab"
                Arguments.of("ababab", "01", 3), // "ab" (idx 0), "ab" (idx 2), "ab" (idx 4)
                Arguments.of("ababab", "10", 2), // "ba" (idx 1), "ba" (idx 3)
                // Complete match across the entire string with overlapping windows
                Arguments.of("ayayayay", "00", 7), // 'a' and 'y' are both vowels. All 7 pairs match "00"
                // Long text with distinct matching blocks separated by invalid data
                // Matches "efe" (idx 1), "efe" (idx 5), and "efe" (idx 14)
                Arguments.of("zefe2efe888xyzefe", "010", 4),
                Arguments.of("Zefe2eFe888xyzEfE", "010", 4),
                // Edge Cases & Boundary Conditions
                Arguments.of("ab", "0101", 0),
                Arguments.of("codes", "11111", 0),
                Arguments.of("aeiouy", "000000", 1),
                // Pattern is longer than source
                Arguments.of("aei", "001000", 0),
                Arguments.of("aaa", "1111", 0),
                // Extra characters
                Arguments.of("aa%a", "000", 0));
    }

    @ParameterizedTest(name = "Test {index}: source = \"{0}\", pattern = \"{1}\" -> expected {2} matches")
    @MethodSource("testData")
    public void patternMatching(String source, String pattern, int expected) {

        Assertions.assertEquals(expected, BinaryStringPatternMatching.getMatches(source, pattern));
        Assertions.assertEquals(expected, BinaryStringPatternMatching.getMatches(source, pattern));
    }

    @Test
    public void throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatches(null, "11"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> BinaryStringPatternMatching.getMatches("", "11"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatches("dfagshj", null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatches("dfagshj", ""));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatches("dfagshj", "2100a"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatchesOptimised(null, "11"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatchesOptimised("", "11"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatchesOptimised("dfagshj", null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatchesOptimised("dfagshj", ""));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BinaryStringPatternMatching.getMatchesOptimised("dfagshj", "2100a"));
    }

}
