package fr.arolla.skocher.trainreservation;

import java.util.List;

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

    @Test
    public void should_a_1_seat_reservation_in_an_empty_coach_be_approved() {
        TicketOffice ticketOffice = new TicketOffice();

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("express_2000", 1)
        );

        Reservation expectReservation = new Reservation(
            "express_2000",
            List.of(new Seat("A", 1)),
            "75bcd15"
        );
        Assertions.assertEquals(expectReservation, reservation);
    }

    @Test
    public void should_a2_seats_reservation_in_an_empty_coach_be_approved() {
        TicketOffice ticketOffice = new TicketOffice();

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("express_2000", 2)
        );

        Reservation expectReservation = new Reservation(
            "express_2000",
            List.of(
                new Seat("A", 1),
                new Seat("A", 2)),
            "75bcd15"
        );
        Assertions.assertEquals(expectReservation, reservation);
    }

}