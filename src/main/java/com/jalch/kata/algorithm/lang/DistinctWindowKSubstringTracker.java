package com.jalch.kata.algorithm.lang;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class DistinctWindowKSubstringTracker {

    // ===========================================================================
    // Given a string s and an integer k, find the number of unique substrings of
    // length k that contain entirely distinct characters with zero repetitions.
    // ===========================================================================

    // Fixed sliding window problem.
    // Time Complexity: O(N) linear time, where N represents the string length.
    // Space Complexity: O(N + K) space, which simplifies directly to O(N) memory.
    public static int find(String input, int k) {
        if (input == null || input.isEmpty() || k < 1)
            return 0;
        Set<String> uniqueKSubstrings = new HashSet<>();
        LinkedHashSet<Character> windowChars = new LinkedHashSet<>();
        int left = 0;
        for (int right = 0; right < input.length(); right++) {
            char currentChar = input.charAt(right);
            if (windowChars.contains(currentChar)) {
                while (windowChars.contains(currentChar)) {
                    windowChars.removeFirst();
                    left++;
                }

            }
            windowChars.addLast(currentChar);
            if (windowChars.size() == k) {
                StringBuilder sb = new StringBuilder();
                for (char c : windowChars)
                    sb.append(c);
                uniqueKSubstrings.add(sb.toString());
                windowChars.removeFirst();
                left++;
            }
        }

        return uniqueKSubstrings.size();
    }

}
