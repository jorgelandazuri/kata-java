package com.jalch.kata.algorithm.lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MultiKeyFrequencyRanking {

    // =======================================================================
    // Given an array of integer identifiers ids, group matching IDs together
    // and return them sorted primarily by their frequency of occurrence in
    // descending order. If two unique IDs share the exact same frequency,
    // sort them by their baseline numerical value in ascending order.
    // =======================================================================

    public static int[] groupAndSortByFrequency(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        Map<Integer, Integer> numsByFrequency = new HashMap<>();
        for (int n : nums)
            numsByFrequency.merge(n, 1, Integer::sum);

        List<Entry<Integer, Integer>> sortedEntries = numsByFrequency.entrySet()
                .stream().sorted((e1, e2) -> {
                    // Frequency sorting.
                    int freq1 = e1.getValue();
                    int freq2 = e2.getValue();
                    if (freq1 != freq2)
                        // Descending order
                        return Integer.compare(freq2, freq1);
                    else {
                        // Id sorting
                        int id1 = e1.getKey();
                        int id2 = e2.getKey();
                        // Ascending
                        return Integer.compare(id1, id2);
                    }
                }).collect(Collectors.toList());

        // int[] result = new int[nums.length]; //-> Use this if the original array
        // should not be touched.
        int i = 0;
        for (Entry<Integer, Integer> e : sortedEntries) {
            int freq = e.getValue();
            for (int t = 0; t < freq; t++) {
                // result[i] = e.getKey(); //-> Use this if the original array should not be
                // touched.
                nums[i] = e.getKey();
                i++;
            }
        }
        // return result; // -> Use this if the original array should not be touched.
        return nums;
    }
}
