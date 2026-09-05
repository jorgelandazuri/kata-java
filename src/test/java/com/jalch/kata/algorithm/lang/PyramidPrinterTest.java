package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class PyramidPrinterTest {

    private static Stream<Arguments> pyramidTestData() {
        return Stream.of(
                Arguments.of(0, ""),
                Arguments.of(1, "*"),
                Arguments.of(2, " *\n***"),
                Arguments.of(3, "  *\n ***\n*****"),
                Arguments.of(4, "   *\n  ***\n *****\n*******"),
                Arguments.of(5, "    *\n   ***\n  *****\n *******\n*********"),
                Arguments.of(10,
                        "         *\n" +
                        "        ***\n" +
                        "       *****\n" +
                        "      *******\n" +
                        "     *********\n" +
                        "    ***********\n" +
                        "   *************\n" +
                        "  ***************\n" +
                        " *****************\n" +
                        "*******************"));
    }

    @ParameterizedTest(name = "Using printPyramidWithStrRepeat - For N={0} should match expected layout")
    @MethodSource("pyramidTestData")
    public void withStringRepeat(int input, String expected) {
        Assertions.assertEquals(expected, PyramidPrinter.printPyramidWithStrRepeat(input));
    }

    @ParameterizedTest(name = "Using printPyramidWithIntStream - For N={0} should match expected layout")
    @MethodSource("pyramidTestData")
    public void withIntStream(int input, String expected) {
        Assertions.assertEquals(expected, PyramidPrinter.printPyramidWithIntStream(input));
    }

    @ParameterizedTest(name = "Using printPyramidWithNestedLoop - For N={0} should match expected layout")
    @MethodSource("pyramidTestData")
    public void withNestedLoop(int input, String expected) {
        Assertions.assertEquals(expected, PyramidPrinter.printPyramidWithNestedLoop(input));
    }

    @Test
    public void negativeInputsThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PyramidPrinter.printPyramidWithStrRepeat(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> PyramidPrinter.printPyramidWithIntStream(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> PyramidPrinter.printPyramidWithNestedLoop(-1));
    }
}
