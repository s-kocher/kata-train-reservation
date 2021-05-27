package fr.arolla.skocher.trainreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketOfficeTest {

    @Test
    public void should_seat_without_booking_reference_be_free() {
        Seat seat = new Seat("A", 1);

        Assertions.assertTrue(seat.isFree());
    }

    @Test
    public void should_seat_with_a_non_empty_booking_reference_be_not_free() {
        Seat seat = new Seat("A", 1);

        seat.setBookingReference("75bcd15");

        Assertions.assertFalse(seat.isFree());
    }

}