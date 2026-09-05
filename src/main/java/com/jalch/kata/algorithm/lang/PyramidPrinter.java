package com.jalch.kata.algorithm.lang;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

// We can render an ASCII art pyramid with N levels by printing N rows of asterisks, 
// where the top row has a single asterisk in the center and each successive row has 
// two additional asterisks on either side.

// Here's what that looks like when N is equal to 3.
//   *  
//  *** 
// *****

// And here's what it looks like when N is equal to 5.
//     *    
//    ***   
//   ***** 
//  ******* 
// *********

// Can you write a program that generates this pyramid with a N value of 10?

public class PyramidPrinter {

    private static final String ASTERISK = "*";

    static String printPyramidWithStrRepeat(int rows) {
        if (rows < 0)
            throw new IllegalArgumentException("Input {%d} should be greater than zero".formatted(rows));
        if (rows == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            int spaces = rows - 1 - i;
            int asterisks = 1 + (i * 2);
            sb.append(" ".repeat(spaces)).append(ASTERISK.repeat(asterisks));
            if (i != rows - 1)
                sb.append("\n");
        }

        return sb.toString();
    }

    static String printPyramidWithIntStream(int rows) {
        if (rows < 0)
            throw new IllegalArgumentException("Input {%d} should be greater than zero".formatted(rows));
        if (rows == 0)
            return "";

        return IntStream.range(0, rows)
                .mapToObj(i -> " ".repeat(rows - 1 - i) + ASTERISK.repeat(1 + 2 * i))
                .collect(Collectors.joining("\n"));
    }

    static String printPyramidWithNestedLoop(int rows) {
        if (rows < 0)
            throw new IllegalArgumentException("Input {%d} should be greater than zero".formatted(rows));
        if (rows == 0)
            return "";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < (rows - 1 - i); j++)
                sb.append(" ");
            for (int j = 0; j < (1 + 2 * i); j++)
                sb.append("*");
            if (i != rows - 1)
                sb.append("\n");
        }

        return sb.toString();
    }

}
