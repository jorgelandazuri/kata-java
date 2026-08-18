package com.jalch.kata.algorithm.search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.jalch.kata.algorithm.search.MostCommonString.find;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class MostCommonStringTest {

    @Test
    public void null_input() {
        assertEquals("", find(null));
    }

    @Test
    public void empty_input() {
        assertEquals("", find(emptyList()));
    }

    @Test
    public void single_element() {
        assertEquals("NY", find(singletonList("NY")));
    }

    @Test
    public void multiple_unique_elements() {
        assertEquals("New York", find(asList("New York", "London", "Barcelona", "Paris")));
    }

    @Test
    public void multiple_not_unique_elements() {
        List<String> input = asList("New York", "London", "Barcelona", "London", "Paris");
        assertEquals("London", find(input));


        input = asList( "Barcelona", "Edinburgh", "Barcelona", "Miami", "Miami", "Barcelona");
        assertEquals("Barcelona", find(input));

        input = asList( "Singapore", "Bangkok", "Singapore", "Bangkok", "Singapore");
        assertEquals("Singapore", find(input));
    }

}