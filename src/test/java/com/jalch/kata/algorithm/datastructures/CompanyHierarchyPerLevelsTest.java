package com.jalch.kata.algorithm.datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyHierarchyPerLevelsTest {

    @Test
    public void first_test_case_example() {

        String input = """
        6
        Sarah Fred
        Sarah Paul
        Fred Hilary
        Fred Jenny
        Jenny James
        """;
        String expected = """
        Sarah
        Fred Paul
        Hilary Jenny
        James
        """;

        assertEquals(expected, CompanyHierarchyPerLevels.findUsingBTree(input));
    }

    @Test
    public void second_test_case_example() {

        String input = """
        5
        June Alex
        June Qing
        Qing Paul
        Qing Gareth
        """;
        String expected = """
        June
        Alex Qing
        Paul Gareth
        """;
        assertEquals(expected, CompanyHierarchyPerLevels.findUsingBTree(input));
    
    }

    @Test
    public void third_test_case_example() {

        String input = """
        6
        Jon Mark
        Jon David
        Mark Paul
        Paul Lee
        Paul Steve
        """;
        String expected = """
        Jon
        Mark David
        Paul
        Lee Steve
        """;
        assertEquals(expected, CompanyHierarchyPerLevels.findUsingBTree(input));
    }

    @Test
    public void fourth_test_case_example() {

        String input = """
        7
        Jon Lee
        Lee Paul
        Paul Mark
        Paul David
        Lee Steve
        Steve Mat
        """;
        String expected = """
        Jon
        Lee
        Paul Steve
        Mark David Mat
        """;
        assertEquals(expected, CompanyHierarchyPerLevels.findUsingBTree(input));
    }

    @Test
    public void fifth_test_case_example() {
        String input = """
        2
        June Alex
        """;
        String expected = """
        June
        Alex
        """;
        assertEquals(expected, CompanyHierarchyPerLevels.findUsingBTree(input));
    }

}