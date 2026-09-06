package com.jalch.kata.algorithm.lang;

import java.util.*;

public class LongestSubstringWithNoRepeatingChars {

    // SLIDING window problem.
    // Given a string, str, return the length of the longest substring without
    // repeating characters.
    // Constraints
    // 1 <= str.length <= 10^5
    // str consists of English letters, digits, and spaces.

    public static int find(String str) {
        if (str == null || str.isEmpty())
            return 0;
        int longest = 0;
        int left = 0;
        Map<Character, Integer> lastFoundAt = new HashMap<Character, Integer>();
        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);

            if (lastFoundAt.containsKey(currentChar)) {
                left = Math.max(left, lastFoundAt.get(currentChar) + 1);
            }

            // Update the last seen index of the current character
            lastFoundAt.put(currentChar, right);
            // Calculate the length of the current window
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    // SOLUTION from www.educative.io:
    public static int findWithSolutionFromEducative(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int windowStart = 0, longest = 0, windowLength = 0, i = 0;

        Hashtable<Character, Integer> lastSeenAt = new Hashtable<Character, Integer>();

        for (i = 0; i < n; i++) {
            if (!lastSeenAt.containsKey(str.charAt(i))) {
                lastSeenAt.put(str.charAt(i), i);
            } else {
                if (lastSeenAt.get(str.charAt(i)) >= windowStart) {
                    windowLength = i - windowStart;
                    if (longest < windowLength) {
                        longest = windowLength;
                    }
                    windowStart = lastSeenAt.get(str.charAt(i)) + 1;
                }
                lastSeenAt.replace(str.charAt(i), i);
            }
        }

        if (longest < i - windowStart) {
            longest = i - windowStart;
        }

        return longest;
    }

}
