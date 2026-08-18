package com.jalch.kata.algorithm.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.jalch.kata.algorithm.sorting.ClosestKPointsFromAPointToAnotherWithCoordinates.getByFullSorting;
import static com.jalch.kata.algorithm.sorting.ClosestKPointsFromAPointToAnotherWithCoordinates.getByUsingMaxHeap;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates")
public class ClosestKPointsFromAPointToAnotherWithCoordinatesTest {

    private static final int ANY_NUMBER = 13;
    private static final List<Integer> ANY_POINT = asList(0, 0);
    private static final List<List<Integer>> ANY_POINTS = asList(asList(3,6), asList(2,6), asList(0,6));

    @Test
    public void null_or_empty_coordinates_input() {
        assertEquals(0, getByFullSorting(ANY_NUMBER, null, Collections.emptyList()).size());
        assertEquals(0, getByFullSorting(ANY_NUMBER, Collections.emptyList(), null).size());

        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, null, Collections.emptyList()).size());
        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, Collections.emptyList(), null).size());
    }

    @Test
    public void zero_or_negative_k_required() {
        assertEquals(0, getByFullSorting(0, ANY_POINT, ANY_POINTS).size());
        assertEquals(0, getByFullSorting(-1, ANY_POINT, ANY_POINTS).size());

        assertEquals(0, getByUsingMaxHeap(0, ANY_POINT, ANY_POINTS).size());
        assertEquals(0, getByUsingMaxHeap(-1, ANY_POINT, ANY_POINTS).size());
    }

    @Test
    public void coordinates_incorrect_size() {
        assertEquals(0, getByFullSorting(ANY_NUMBER, asList(3), ANY_POINTS).size());
        assertEquals(0, getByFullSorting(ANY_NUMBER, asList(3, 4, 5), ANY_POINTS).size());
        assertEquals(0, getByFullSorting(ANY_NUMBER, ANY_POINT, asList(asList(3))).size());
        assertEquals(0, getByFullSorting(ANY_NUMBER, ANY_POINT, asList(asList(3, 4, 5))).size());

        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, asList(3), ANY_POINTS).size());
        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, asList(3, 4, 5), ANY_POINTS).size());
        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, ANY_POINT, asList(asList(3))).size());
        assertEquals(0, getByUsingMaxHeap(ANY_NUMBER, ANY_POINT, asList(asList(3, 4, 5))).size());
    }

    @Test
    public void more_k_request_than_available() {
        List<List<Integer>> result = getByFullSorting(4, ANY_POINT, ANY_POINTS);
        assertEquals(3, result.size());
        assertEquals(asList(0,6), result.get(0));
        assertEquals(asList(2,6), result.get(1));
        assertEquals(asList(3,6), result.get(2));

        result = getByUsingMaxHeap(4, ANY_POINT, ANY_POINTS);
        assertEquals(3, result.size());
        assertEquals(asList(0,6), result.get(0));
        assertEquals(asList(2,6), result.get(1));
        assertEquals(asList(3,6), result.get(2));
    }

    @Test //Executed in ~1,6 seconds in average (2.5 GHz Intel Core i7)
    public void k_items_sorted_by_distance_by_full_coordinates_sorting() {
        List<Integer> randomPosition = getRandomPosition();
        List<List<Integer>> result = getByFullSorting(5, randomPosition, getRandomCoordinates(10));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByFullSorting(500, randomPosition, getRandomCoordinates(100_000));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByFullSorting(5000, randomPosition, getRandomCoordinates(1_000_000));
        assertSortedByDistanceFor(result, randomPosition);
    }

    @Test //Executed in ~2.4 seconds in average (2.5 GHz Intel Core i7)
    public void all_items_sorted_by_distance_by_full_coordinates_sorting() {
        List<Integer> randomPosition = getRandomPosition();
        List<List<Integer>> result = getByFullSorting(10, randomPosition, getRandomCoordinates(10));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByFullSorting(100_000, randomPosition, getRandomCoordinates(100_000));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByFullSorting(1_000_000, randomPosition, getRandomCoordinates(1_000_000));
        assertSortedByDistanceFor(result, randomPosition);
    }

    @Test //Executed in ~0.4 seconds in average (2.5 GHz Intel Core i7)
    public void k_items_sorted_by_distance_by_using_max_heap() {
        List<Integer> randomPosition = getRandomPosition();
        List<List<Integer>> result = getByUsingMaxHeap(5, randomPosition, getRandomCoordinates(10));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByUsingMaxHeap(500, randomPosition, getRandomCoordinates(100_000));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByUsingMaxHeap(5000, randomPosition, getRandomCoordinates(1_000_000));
        assertSortedByDistanceFor(result, randomPosition);
    }

    @Test //Executed in ~0,9 seconds in average (2.5 GHz Intel Core i7)
    public void all_items_sorted_by_distance_by_using_max_heap() {
        List<Integer> randomPosition = getRandomPosition();
        List<List<Integer>> result = getByUsingMaxHeap(10, randomPosition, getRandomCoordinates(10));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByUsingMaxHeap(100_000, randomPosition, getRandomCoordinates(100_000));
        assertSortedByDistanceFor(result, randomPosition);

        randomPosition = getRandomPosition();
        result = getByUsingMaxHeap(1_000_000, randomPosition, getRandomCoordinates(1_000_000));
        assertSortedByDistanceFor(result, randomPosition);
    }

    private List<Integer> getRandomPosition() {
        return asList(new Random().nextInt(), new Random().nextInt());
    }

    private void assertSortedByDistanceFor(List<List<Integer>> result, List<Integer> randomPosition) {
        for (int i = 0; i < result.size() - 1; i++) {
            boolean success = distance(randomPosition, result.get(i)) <= distance(randomPosition, result.get(i + 1));
            assertTrue(success);
        }
    }

    private double distance(List<Integer> from, List<Integer> to) {
        return sqrt(pow(from.get(0) - to.get(0), 2) + pow(from.get(1) - to.get(1), 2));
    }

    private List<List<Integer>> getRandomCoordinates(int numberOfCoordinates) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numberOfCoordinates; i++) {
            result.add(Arrays.asList(new Random().nextInt(), new Random().nextInt()));
        }
        return result;
    }
}