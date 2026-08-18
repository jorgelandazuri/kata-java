package com.jalch.kata.algorithm.sorting;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static java.lang.Double.compare;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static java.util.Comparator.naturalOrder;

import java.util.Collections;

class ClosestKPointsFromAPointToAnotherWithCoordinates {
    //coordinates.size() = n

    //Time complexity: O(n) + O(n log n) + 0(1) ~ O(n log n)
    static List<List<Integer>> getByFullSorting(int k, List<Integer> position, List<List<Integer>> coordinates) {
        if(notValidInput(k, position, coordinates)) return Collections.emptyList();

        //O(n)
        List<CoordinatesWithDistance> coordinatesWithDistances = getCoordinatesWithDistance(position, coordinates);

        //https://stackoverflow.com/questions/31301471/big-o-complexity-of-java-util-stream-streamt-sorted
        //O(n log n)
        List<List<Integer>> sortedCoordinates = coordinatesWithDistances.stream().sorted(naturalOrder())
                .map(CoordinatesWithDistance::getCoordinates)
                .collect(Collectors.toList());

        return k <= sortedCoordinates.size()
                ? sortedCoordinates.subList(0, k) //O(1)
                : sortedCoordinates;
    }

    //Time complexity: O(n) + O(k) + 0((n-k) log k) + O(k log k)
    // ~ 0((n-k) log k) if n-k > 0
    // ~ 0(k log k) if n == k
    //      => But would tend to be faster than full sort one as the max heap is sorted by levels at least (see test)
    static List<List<Integer>> getByUsingMaxHeap(int k, List<Integer> position, List<List<Integer>> coordinates) {
        if(notValidInput(k, position, coordinates)) return Collections.emptyList();

        //O(n)
        List<CoordinatesWithDistance> coordinatesWithDistances = getCoordinatesWithDistance(position, coordinates);

        //0(k)
        PriorityQueue<CoordinatesWithDistance> maxHeap = new PriorityQueue<>(naturalOrder());
        int resultSize = k <= coordinates.size() ? k : coordinates.size();
        for(int i = 0; i < resultSize; i++) {
            maxHeap.add(coordinatesWithDistances.get(i));
        }

        //0((n-k) log k) -> Iterating (n-k) times over a heap of size k.
        for(int i = resultSize; i < coordinatesWithDistances.size(); i++){
            CoordinatesWithDistance current = coordinatesWithDistances.get(i);
            if(maxHeap.peek().getDistance() > current.getDistance()){
                maxHeap.poll();
                maxHeap.add(current);
            }
        }
        //Need to sort the result, as can't guarantee order with nodes at same level.
        //O(k log k)
        return maxHeap.stream().sorted(naturalOrder()).map(i -> i.getCoordinates()).collect(Collectors.toList());
    }

    private static boolean notValidInput(int k , List<Integer> position, List<List<Integer>> coordinates) {
        return k < 1 || position == null || position.size() != 2
                || coordinates == null || coordinates.isEmpty()
                || coordinates.stream().anyMatch(c -> c.size() != 2);
    }

    private static List<CoordinatesWithDistance> getCoordinatesWithDistance(List<Integer> position, List<List<Integer>> coordinates) {
        return coordinates.stream()
                .map(c -> new CoordinatesWithDistance(c.get(0), c.get(1), position.get(0), position.get(1)))
                .collect(Collectors.toList());
    }

    static class CoordinatesWithDistance implements Comparable<CoordinatesWithDistance> {
        private final int x;
        private final int y;
        private final double distance;

        CoordinatesWithDistance(int x, int y, int xFrom, int yFrom) {
            this.x = x;
            this.y = y;
            this.distance = sqrt(pow(xFrom-x, 2) + pow(yFrom-y, 2));
        }

        double getDistance() {
            return distance;
        }

        List<Integer> getCoordinates() {
            return asList(x,y);
        }

        @Override
        public int compareTo(CoordinatesWithDistance other) {
            //ascending order
            return compare(this.distance, other.getDistance());
            //descending order
            //return compare(other.getDistance(), this.distance);
        }
    }

}
