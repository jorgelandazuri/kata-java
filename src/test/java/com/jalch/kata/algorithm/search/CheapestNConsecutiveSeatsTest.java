package com.jalch.kata.algorithm.search;

import com.jalch.kata.algorithm.search.CheapestNConsecutiveSeats.Seat;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class CheapestNConsecutiveSeatsTest {

    private final Seat[][] SOME_PLANE_SEATS = this.somePlane();

    @Test
    public void negative_required_seats() {
        assertEquals(0, CheapestNConsecutiveSeats.find(SOME_PLANE_SEATS, -1).length);
    }

    @Test
    public void zero_required_seats() {
        assertEquals(0, CheapestNConsecutiveSeats.find(SOME_PLANE_SEATS, 0).length);
    }

    @Test
    public void plane_with_no_seats() {
        int anyRequiredSeats = 5;
        assertEquals(0, CheapestNConsecutiveSeats.find(new Seat[0][0], anyRequiredSeats).length);
    }

    @Test
    public void required_more_seats_than_columns_available() {
        assertEquals(0, CheapestNConsecutiveSeats.find(SOME_PLANE_SEATS, 7).length);
    }

    @Test
    public void cheapest_in_one_row_plane_with_all_available_seats() {
        Seat[][] inputPlane = new Seat[1][6];
        Seat[] row = {
                new Seat(9, 0, 0,  true),
                new Seat(8, 0, 1, true),
                new Seat(7, 0, 2, true),
                new Seat(6, 0, 3, true),
                new Seat(5, 0, 4, true),
                new Seat(4, 0, 5, true)};
        inputPlane[0] = row;
        Seat[] result = CheapestNConsecutiveSeats.find(inputPlane, 6);
        assertEquals(6, result.length);
        assertEquals(row[0].seatId, result[0].seatId);
        assertEquals(row[1].seatId, result[1].seatId);
        assertEquals(row[2].seatId, result[2].seatId);
        assertEquals(row[3].seatId, result[3].seatId);
        assertEquals(row[4].seatId, result[4].seatId);
        assertEquals(row[5].seatId, result[5].seatId);

        row = new Seat[]{
                new Seat(1, 0, 0,  true),
                new Seat(2, 0, 1, true),
                new Seat(7, 0, 2, true),
                new Seat(6, 0, 3, true),
                new Seat(5, 0, 4, true),
                new Seat(4, 0, 5, true)};
        inputPlane[0] = row;
        result = CheapestNConsecutiveSeats.find(inputPlane, 2);
        assertEquals(2, result.length);
        assertEquals(row[0].seatId, result[0].seatId);
        assertEquals(row[1].seatId, result[1].seatId);

        row = new Seat[]{
                new Seat(3, 0, 0,  true),
                new Seat(5, 0, 1, true),
                new Seat(2, 0, 2, true),
                new Seat(4, 0, 3, true),
                new Seat(1, 0, 4, true),
                new Seat(4, 0, 5, true)};
        inputPlane[0] = row;
        result = CheapestNConsecutiveSeats.find(inputPlane, 3);
        assertEquals(3, result.length);
        assertEquals(row[2].seatId, result[0].seatId);
        assertEquals(row[3].seatId, result[1].seatId);
        assertEquals(row[4].seatId, result[2].seatId);
    }

    @Test
    public void a_row_with_no_spaces() {
        Seat[][] inputPlane = new Seat[1][10];
        Seat[] row = {
                new Seat(9, 0,0, false),
                new Seat(8, 0, 1, false),
                new Seat(7, 0, 2, false),
                new Seat(6, 0, 3, false),
                new Seat(5, 0, 4, false),
                new Seat(5, 0, 5, false),
                new Seat(1, 0, 6, false),
                new Seat(1, 0, 7, false),
                new Seat(4, 0, 8, false),
                new Seat(5, 0, 9, false)};
        inputPlane[0] = row;
        assertEquals(0, CheapestNConsecutiveSeats.find(inputPlane, 2).length);
        assertEquals(0, CheapestNConsecutiveSeats.find(inputPlane, 1).length);

    }

    @Test
    public void one_row_plane_with_multiple_subset_of_seats_free_find_cheapest() {
        Seat[][] inputPlane = new Seat[1][10];
        Seat[] row = {
                new Seat(9, 0,0, true),
                new Seat(8, 0, 1, false),
                new Seat(7, 0, 2, true),
                new Seat(6, 0, 3, true),
                new Seat(5, 0, 4, false),
                new Seat(5, 0, 5, true),
                new Seat(1, 0, 6, true),
                new Seat(1, 0, 7, true),
                new Seat(4, 0, 8, true),
                new Seat(5, 0, 9, false)};
        inputPlane[0] = row;
        Seat[] result = CheapestNConsecutiveSeats.find(inputPlane, 2);
        assertEquals(2, result.length);
        assertEquals(row[6].seatId, result[0].seatId);
        assertEquals(row[7].seatId, result[1].seatId);
    }


    @Test
    public void a_full_plane() {
        Seat[][] inputPlane = new Seat[4][10];
        Seat[] rowOne = {
                new Seat(9, 0,0, true),
                new Seat(8, 0, 1, false),
                new Seat(7, 0, 2, true),
                new Seat(6, 0, 3, true),
                new Seat(5, 0, 4, false),
                new Seat(5, 0, 5, true),
                new Seat(1, 0, 6, true),
                new Seat(1, 0, 7, true),
                new Seat(4, 0, 8, true),
                new Seat(5, 0, 9, false)};
        Seat[] rowTwoNoSpaces = {
                new Seat(9, 1,0, false),
                new Seat(8, 1, 1, false),
                new Seat(7, 1, 2, false),
                new Seat(6, 1, 3, false),
                new Seat(5, 1, 4, false),
                new Seat(5, 1, 5, false),
                new Seat(1, 1, 6, false),
                new Seat(1, 1, 7, false),
                new Seat(4, 1, 8, false),
                new Seat(5, 1, 9, false)};
        Seat[] rowThreeSamePrice = {
                new Seat(9, 2,0, true),
                new Seat(9, 2, 1, true),
                new Seat(9, 2, 2, true),
                new Seat(9, 2, 3, true),
                new Seat(9, 2, 4, true),
                new Seat(9, 2, 5, true),
                new Seat(9, 2, 6, true),
                new Seat(9, 2, 7, true),
                new Seat(9, 2, 8, true),
                new Seat(9, 2, 9, true)};
        Seat[] rowFourWithCheapestSC = {
                new Seat(9, 3,0, false),
                new Seat(9, 3, 1, true),
                new Seat(9, 3, 2, true),
                new Seat(9, 3, 3, true),
                new Seat(1, 3, 4, false),
                new Seat(1, 3, 5, true),
                new Seat(1, 3, 6, true),
                new Seat(1, 3, 7, true),
                new Seat(9, 3, 8, true),
                new Seat(1, 3, 9, false)};

        inputPlane[0] = rowOne;
        inputPlane[1] = rowTwoNoSpaces;
        inputPlane[2] = rowThreeSamePrice;
        inputPlane[3] = rowFourWithCheapestSC;
        Seat[] result = CheapestNConsecutiveSeats.find(inputPlane, 3);
        assertEquals(3, result.length);
        assertEquals(rowFourWithCheapestSC[5].seatId, result[0].seatId);
        assertEquals(rowFourWithCheapestSC[6].seatId, result[1].seatId);
        assertEquals(rowFourWithCheapestSC[7].seatId, result[2].seatId);
    }

    private Seat[][] somePlane() {
        Seat[][] plane = new Seat[40][6];
        for(int r = 0; r < 40; r ++){
            for(int c = 0; c < 6; c++ ){
                plane[r][c] = new Seat(randomPrice(), r, c, new Random().nextBoolean());
            }
        }
        return plane;
    }

    private static float randomPrice() {
        return new Random().nextFloat() * (5000.00F - 0);
    }
}