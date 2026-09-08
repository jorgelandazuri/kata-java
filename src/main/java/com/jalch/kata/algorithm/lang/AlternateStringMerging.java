package com.jalch.kata.algorithm.lang;

public class AlternateStringMerging {

    // You are given two strings, s1 and s2. Merge the strings by adding characters
    // in alternating order,
    // starting with s1. If a string is longer than the other, append the additional
    // characters onto the
    // end of the merged string. Return the final merged string.

    // O(N+M) space and time complexity. N=s1Length - M=s2Length
    public static String merge(String s1, String s2) {
        if (s1 == null || s1.isEmpty())
            return s2;
        if (s2 == null || s2.isEmpty())
            return s1;

        int s1Length = s1.length();
        int s2Length = s2.length();
        StringBuilder sb = new StringBuilder(s1Length + s2Length);
        int i = 0;
        while (i < s1Length && i < s2Length) {
            sb.append(s1.charAt(i));
            sb.append(s2.charAt(i));
            i++;
        }
        if (i < s1Length)
            sb.append(s1.substring(i));
        else if (i < s2Length)
            sb.append(s2.substring(i));

        return sb.toString();
    }

}
