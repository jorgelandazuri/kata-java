package com.jalch.kata.algorithm.lang;

import java.util.Map;

public class BinaryStringPatternMatching {

    // Given a string source and a pattern string pattern consisting only of
    // '0's and '1's. A '0' matches any vowel (a, e, i, o, u, y), and a '1'
    // matches any consonant. Return the number of substrings in source of
    // length equal to pattern that match the pattern rules.

    private static final String VOWELS_LOWERCASE = "aeiouy";
    private static final String CONSONANTS_LOWERCASE = "bcdfghjklmnpqrstvwxz";
    // O(N*M) solution.
    private static final Map<Character, String> PATTERN_MAP = Map.of('0', VOWELS_LOWERCASE, '1', CONSONANTS_LOWERCASE);
    private static final String PATTERN_REGEX = "[01]+";
    // O(N) solution
    // Quick boolean lookup for vowels and consonants
    private static final boolean[] IS_VOWEL = new boolean[256];
    private static final boolean[] IS_CONSONANT = new boolean[256];
    static {
        // Initialize vowels
        for (char c : VOWELS_LOWERCASE.toCharArray())
            IS_VOWEL[c] = true;
        // Initialize consonants
        for (char c : CONSONANTS_LOWERCASE.toCharArray())
            IS_CONSONANT[c] = true;
    }

    // O(N * M) where N is source length and M is pattern length.
    // Benchmark:
    // source string of 100,000 random alpha-numeric characters and a pattern string
    // of length 15 ("010101010101010").
    // Execution time: ~54.2 ms
    public static int getMatches(String source, String pattern) {
        boolean invalidSource = source == null || source.isBlank();
        boolean invalidPattern = pattern == null || pattern.isBlank() || !pattern.matches(PATTERN_REGEX);
        if (invalidSource || invalidPattern)
            throw new IllegalArgumentException("Invalid source %s or pattern %s".formatted(source, pattern));
        // Pattern won't match if source is shorter than pattern.
        if (source.length() < pattern.length())
            return 0;

        source = source.toLowerCase();
        int matchesCount = 0;
        for (int left = 0; left <= source.length() - pattern.length(); left++) {
            if (matchesForSubstring(source.substring(left, left + pattern.length()), pattern))
                matchesCount++;
        }
        return matchesCount;
    }

    private static boolean matchesForSubstring(String sourceSubString, String pattern) {
        if (sourceSubString.length() < pattern.length())
            return false;
        for (int i = 0; i < pattern.length(); i++) {
            char currentCharPattern = pattern.charAt(i);
            if (!PATTERN_MAP.get(currentCharPattern).contains(sourceSubString.charAt(i) + ""))
                return false;
        }
        return true;
    }

    // O(N) optimised solution
    // Benchmark:
    // source string of 100,000 random alpha-numeric characters and a pattern string
    // of length 15 ("010101010101010").
    // Execution time: ~3.1 ms (~17x faster)
    public static int getMatchesOptimised(String source, String pattern) {
        boolean invalidSource = source == null || source.isBlank();
        boolean invalidPattern = pattern == null || pattern.isBlank() || !pattern.matches(PATTERN_REGEX);

        if (invalidSource || invalidPattern) {
            throw new IllegalArgumentException("Invalid source or pattern structure");
        }

        int n = source.length();
        int m = pattern.length();
        if (n < m)
            return 0;

        int matchesCount = 0;
        source = source.toLowerCase();

        // 2. Sliding window check
        for (int i = 0; i <= n - m; i++) {
            boolean fullyMatches = true;

            for (int j = 0; j < m; j++) {
                char sourceChar = source.charAt(i + j);
                char patternChar = pattern.charAt(j);
                // If it's a special character/number, it won't be index-safe if out of ASCII
                // bounds.
                if (sourceChar >= 256) {
                    fullyMatches = false;
                    break;
                }
                // Check constraints safely
                if (patternChar == '0' && !IS_VOWEL[sourceChar]) {
                    fullyMatches = false;
                    break;
                } else if (patternChar == '1' && !IS_CONSONANT[sourceChar]) {
                    fullyMatches = false;
                    break;
                }
            }

            if (fullyMatches)
                matchesCount++;
        }

        return matchesCount;
    }

}
