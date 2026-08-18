package com.jalch.kata.algorithm.sorting;

//https://www.hackerrank.com/challenges/mark-and-toys/problem
//Mark and Jane are very happy after having their first child. Their son loves toys, so Mark wants to buy some.
// There are a number of different toys lying in front of him, tagged with their prices. Mark has only a certain amount
// to spend, and he wants to maximize the number of toys he buys with this money.
// Given a list of prices and an amount to spend, what is the maximum number of toys Mark can buy? For example, if  and
// Mark has  to spend, he can buy items  for , or  for  units of currency. He would choose the first group of  items.
// **Function Description**
// Complete the function maximumToys in the editor below. It should return an integer representing the maximum number of
// toys Mark can purchase.
// maximumToys has the following parameter(s):
// prices: an array of integers representing toy prices
// k: an integer, Mark's budget
// return value: An integer that denotes the maximum number of toys Mark can buy for his son.

import java.util.*;

public class MaxNumberOfPricedItemsWithBudget {

    private final ToyPriceMinSorter<Collection<Integer>> priorityQueueSorter = new PriorityQueueSorter();
    private final ToyPriceMinSorter<Collection<Integer>> insertionSorter = new InsertionSorter();
    private final ToyPriceMinSorter<int[]> doublePivotQuickSorter = new DoublePivotQuickSorter();

    public long lastMethodExecutionTime = 0L;

    public int maximumToysWithMinHeapSort(int[] toyPrices, int budget) {
        long initialTime = System.currentTimeMillis();
        int maximumToys = maximumToys(priorityQueueSorter.sort(toyPrices), budget);
        lastMethodExecutionTime = System.currentTimeMillis() - initialTime;
        return maximumToys;
    }

    public int maximumToysWithInsertionSort(int[] toyPrices, int budget) {
        long initialTime = System.currentTimeMillis();
        int maximumToys = maximumToys(insertionSorter.sort(toyPrices), budget);
        lastMethodExecutionTime = System.currentTimeMillis() - initialTime;
        return maximumToys;
    }

    public int maximumToysWithQuickSort(int[] toyPrices, int budget) {
        long initialTime = System.currentTimeMillis();
        int maximumToys = maximumToys(doublePivotQuickSorter.sort(toyPrices), budget);
        lastMethodExecutionTime = System.currentTimeMillis() - initialTime;
        return maximumToys;
    }

    private int maximumToys(int[] toyPrices, int budget) {
        int totalToysCost = 0;
        int itemsInBudget = 0;
        for (Integer price : toyPrices) {
            if (price > 0 && (totalToysCost + price) <= budget) {
                totalToysCost += price;
                itemsInBudget++;
            }
        }
        return itemsInBudget;
    }

    private int maximumToys(Collection<Integer> toyPrices, int budget) {
        int totalToysCost = 0;
        int itemsInBudget = 0;
        for (Integer price : toyPrices) {
            if (price > 0 && (totalToysCost + price) <= budget) {
                totalToysCost += price;
                itemsInBudget++;
            }
        }
        return itemsInBudget;
    }

    private interface ToyPriceMinSorter<T> {
        T sort(int[] toyPrices);
    }

    private class PriorityQueueSorter implements ToyPriceMinSorter<Collection<Integer>> {
        @Override
        public Collection<Integer> sort(int[] toyPrices) {
            PriorityQueue<Integer> orderedList = new PriorityQueue<>();
            for (int price : toyPrices) {
                orderedList.add(price);
            }
            return orderedList;
        }
    }

    private class InsertionSorter implements ToyPriceMinSorter<Collection<Integer>> {
        @Override
        public Collection<Integer> sort(int[] toyPrices) {
            LinkedList<Integer> orderedList = new LinkedList<>();
            if (toyPrices.length > 0) {
                orderedList.addFirst(toyPrices[0]);
                for (int i = 1; i < toyPrices.length; i++) {
                    boolean inserted = false;
                    for (int j = 0; j < orderedList.size() && !inserted; j++) {
                        if (toyPrices[i] < orderedList.get(j)) {
                            orderedList.add(j, toyPrices[i]);
                            inserted = true;
                        }
                    }
                    if (!inserted) {
                        orderedList.add(toyPrices[i]);
                    }
                }
            }
            return orderedList;
        }
    }

    private class DoublePivotQuickSorter implements ToyPriceMinSorter<int[]> {
        @Override
        public int[] sort(int[] toyPrices) {
            Arrays.sort(toyPrices);
            return toyPrices;
        }
    }

}
