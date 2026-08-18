package com.jalch.kata.algorithm.sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.Random;
import static java.lang.Integer.MAX_VALUE;


public class MaxNumberOfPricedItemsWithBudgetTest {

    private static final int ANY_BUDGET = 9999;
    private static final int[] ANY_TOY_PRICES = {3, 4345, 345, 23, 4234, 9};

    private MaxNumberOfPricedItemsWithBudget underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MaxNumberOfPricedItemsWithBudget();
    }

    @Test
    public void no_toy_prices_and_any_budget() {
        assertEquals(0, underTest.maximumToysWithMinHeapSort(new int[]{}, ANY_BUDGET));
        assertEquals(0, underTest.maximumToysWithInsertionSort(new int[]{}, ANY_BUDGET));
        assertEquals(0, underTest.maximumToysWithQuickSort(new int[]{}, ANY_BUDGET));
    }

    @Test
    public void any_toy_prices_and_negative_budget() {
        assertEquals(0, underTest.maximumToysWithMinHeapSort(ANY_TOY_PRICES, -1));
        assertEquals(0, underTest.maximumToysWithInsertionSort(ANY_TOY_PRICES, -1));
        assertEquals(0, underTest.maximumToysWithQuickSort(ANY_TOY_PRICES, -1));
    }

    @Test
    public void any_toy_prices_and_no_budget() {
        assertEquals(0, underTest.maximumToysWithMinHeapSort(ANY_TOY_PRICES, 0));
        assertEquals(0, underTest.maximumToysWithInsertionSort(ANY_TOY_PRICES, 0));
        assertEquals(0, underTest.maximumToysWithQuickSort(ANY_TOY_PRICES, 0));
    }

    @Test
    public void one_toy_price_and_not_enough_budget() {
        assertEquals(0, underTest.maximumToysWithMinHeapSort(new int[]{3}, 2));
        assertEquals(0, underTest.maximumToysWithInsertionSort(new int[]{3}, 2));
        assertEquals(0, underTest.maximumToysWithQuickSort(new int[]{3}, 2));
    }

    @Test
    public void one_toy_price_and_enough_budget() {
        assertEquals(1, underTest.maximumToysWithMinHeapSort(new int[]{3}, 3));
        assertEquals(1, underTest.maximumToysWithInsertionSort(new int[]{3}, 3));
        assertEquals(1, underTest.maximumToysWithQuickSort(new int[]{3}, 3));
    }

    @Test
    public void badly_tagged_negative_price_enough_budget() {
        assertEquals(2, underTest.maximumToysWithMinHeapSort(new int[]{23, -1, 234}, 257));
        assertEquals(2, underTest.maximumToysWithInsertionSort(new int[]{23, -1, 234}, 257));
        assertEquals(2, underTest.maximumToysWithQuickSort(new int[]{23, -1, 234}, 257));
    }

    @Test
    public void many_toys_prices_and_enough_budget_for_first_one() {
        assertEquals(1, underTest.maximumToysWithMinHeapSort(new int[]{3, 5, 6, 9}, 3));
        assertEquals(1, underTest.maximumToysWithInsertionSort(new int[]{3, 5, 6, 9}, 3));
        assertEquals(1, underTest.maximumToysWithQuickSort(new int[]{3, 5, 6, 9}, 3));
    }

    @Test
    public void many_toys_prices_and_enough_budget_for_one_of_them_not_first_only() {
        assertEquals(1, underTest.maximumToysWithMinHeapSort(new int[]{5, 3, 9, 4}, 3));
        assertEquals(1, underTest.maximumToysWithInsertionSort(new int[]{5, 3, 9, 4}, 3));
        assertEquals(1, underTest.maximumToysWithQuickSort(new int[]{5, 3, 9, 4}, 3));
    }

    @Test
    public void many_toys_prices_and_enough_budget_for_as_many_as_possible() {
        assertEquals(4, underTest.maximumToysWithMinHeapSort(new int[]{2, 4, 5, 3, 9, 1, 3}, 9));
        assertEquals(4, underTest.maximumToysWithInsertionSort(new int[]{2, 4, 5, 3, 9, 1, 3}, 9));
        assertEquals(4, underTest.maximumToysWithQuickSort(new int[]{2, 4, 5, 3, 9, 1, 3}, 9));
    }

    @Test
    public void big_number_of_toy_prices_with_random_budget_best_algorithm() {
        assertEquals(0L, underTest.lastMethodExecutionTime);
        assertSortingOptionsTimeEfficiencyWithNumberOfPrices(49);
        assertSortingOptionsTimeEfficiencyWithNumberOfPrices(99);
        assertSortingOptionsTimeEfficiencyWithNumberOfPrices(999);
        assertSortingOptionsTimeEfficiencyWithNumberOfPrices(1999);
    }

    private void assertSortingOptionsTimeEfficiencyWithNumberOfPrices(int numberOfPrices) {
        int[] prices = getRandomPrices(numberOfPrices);

        underTest.maximumToysWithQuickSort(prices, MAX_VALUE);
        long withQuickSortTime = underTest.lastMethodExecutionTime;
        underTest.maximumToysWithMinHeapSort(prices, MAX_VALUE);
        long withHeapSortTime = underTest.lastMethodExecutionTime;
        underTest.maximumToysWithInsertionSort(prices, MAX_VALUE);
        long withInsertionSortTime = underTest.lastMethodExecutionTime;
        //Verify that in average:
        //Time complexity of quick sort and min heapsort are less that insertion sort.
        System.out.println("Number of prices: " + numberOfPrices);
        System.out.println("Double pivot Quicksort took: " + withQuickSortTime + " ms");
        System.out.println("Min Heapsort took: " + withHeapSortTime + " ms");
        System.out.println("InsertionSort took: " + withInsertionSortTime + " ms");
        System.out.println();
    }

    private static int[] getRandomPrices(int numberOfPrices) {
        return new Random().ints(numberOfPrices, 0, MAX_VALUE).toArray();
    }
}