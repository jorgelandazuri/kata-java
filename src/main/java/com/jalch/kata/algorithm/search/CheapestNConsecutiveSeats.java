package com.jalch.kata.algorithm.search;


import static java.util.Arrays.copyOfRange;

//Find the cheapest seat combination for N people given a 2D matrix of airplane seats on the same row.
//The input gives the position of each seat, whether they are already taken and the price of the seat.
//The value of N is provided

public class CheapestNConsecutiveSeats {

    public static Seat[] find(Seat[][] plane, int requiredSeats) {
        return validInput(plane, requiredSeats) ? new Seat[0] : process(plane, requiredSeats);
    }

    private static Seat[] process(Seat[][] plane, int requiredSeats) {

        SeatCombination cheapestSCInPlane = null;
        int rowWithCheapestSC = 0;
        for (int r = 0; r < plane.length; r++) {
            SeatCombination seatCombinationInRow = findCheapestSeatCombinationInRow(plane[r], 0, requiredSeats);
            if (cheapestSCInPlane == null) {
                cheapestSCInPlane = seatCombinationInRow;
                rowWithCheapestSC = r;
            } else if (seatCombinationInRow != null && cheapestSCInPlane.totalPrice > seatCombinationInRow.totalPrice) {
                cheapestSCInPlane = seatCombinationInRow;
                rowWithCheapestSC = r;
            }
        }

        return cheapestSCInPlane != null
                ? copyOfRange(plane[rowWithCheapestSC],
                         cheapestSCInPlane.lastSCIndexInRow - requiredSeats + 1,
                           cheapestSCInPlane.lastSCIndexInRow + 1)
                : new Seat[0];

    }

    private static SeatCombination findCheapestSeatCombinationInRow(Seat[] seatRow, int from, int requiredSeats) {

        if (seatRow.length - from < requiredSeats) return null;

        float currentSCPrice = 0;
        int currentSCLastSeatIndex = 0;
        float cheapestSCPrice = Float.MAX_VALUE;
        boolean minSeatsAvailable = false;
        SeatCombination restOfRowSC = null;

        for (int c = from; c < seatRow.length; c++) {

            if (seatRow[c].available) {
                currentSCPrice += seatRow[c].price;
                int seatCount = c - from + 1;
                if (seatCount >= requiredSeats) {
                    if (seatCount == requiredSeats)
                        minSeatsAvailable = true;
                    if (cheapestSCPrice > currentSCPrice) {
                        cheapestSCPrice = currentSCPrice;
                        currentSCLastSeatIndex = c;
                    }
                    currentSCPrice -= seatRow[c + 1 - requiredSeats].price;
                }
            } else {
                restOfRowSC = findCheapestSeatCombinationInRow(seatRow, c + 1, requiredSeats);
                break;
            }
        }

        SeatCombination currentSC = new SeatCombination(cheapestSCPrice, currentSCLastSeatIndex);

        if (minSeatsAvailable && restOfRowSC != null) {
            return restOfRowSC.totalPrice < cheapestSCPrice ? restOfRowSC : currentSC;
        } else if (!minSeatsAvailable && restOfRowSC != null) {
            return restOfRowSC;
        } else if (minSeatsAvailable) {
            return currentSC;
        } else {
            return null;
        }
    }

    private static boolean validInput(Seat[][] plane, int requiredSeats) {
        return requiredSeats <= 0 || plane.length == 0 || requiredSeats > plane[0].length;
    }

    static class Seat {
        public final float price;
        public final String seatId;
        public final boolean available;
        public final int rowIndex;
        public final int columnIndex;

        Seat(float price, int rowIndex, int columnIndex, boolean available) {
            this.price = price;
            this.seatId = getSeatIdFrom(rowIndex, columnIndex);
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.available = available;
        }

        private static String getSeatIdFrom(int row, int column) {
            return String.valueOf(row + 1) + String.valueOf((char) ((int) 'A' + column));
        }
    }

    static class SeatCombination {
        private final float totalPrice;
        private final int lastSCIndexInRow;

        public SeatCombination(float totalPrice, int lastSCIndexInRow) {
            this.totalPrice = totalPrice;
            this.lastSCIndexInRow = lastSCIndexInRow;
        }

        public float getTotalPrice() {
            return totalPrice;
        }

        public int getLastSCIndexInRow() {
            return lastSCIndexInRow;
        }
    }
}
