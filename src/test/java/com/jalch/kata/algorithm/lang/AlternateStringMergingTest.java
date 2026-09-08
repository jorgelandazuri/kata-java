package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AlternateStringMergingTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, "adsf", "adsf"),
                Arguments.of("", "adsf", "adsf"),
                Arguments.of("adsf", null, "adsf"),
                Arguments.of("adsf", "", "adsf"),
                Arguments.of("1357", "2468", "12345678"),
                Arguments.of("1", "23", "123"),
                Arguments.of("1", "2345678", "12345678"),
                Arguments.of("13", "2", "123"),
                Arguments.of("1345678", "2", "12345678"));
    }

    @ParameterizedTest(name = "Test {index}: for s1={0} and s2={1} expected merged string should be = {2}")
    @MethodSource("testData")
    void testFindAndFindOptimised(String s1, String s2, String expected) {
        Assertions.assertEquals(expected, AlternateStringMerging.merge(s1, s2));
    }
}
