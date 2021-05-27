package fr.arolla.skocher.trainreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketOffice {
    
    public Reservation makeReservation(ReservationRequest request) {
        //TODO: implement this code!
        return null;
    }

    public static class TicketOfficeTest {

        @Test
        public void should_seat_without_booking_reference_be_free() {
            Seat seat = new Seat("A", 1);

            Assertions.assertTrue(seat.isFree());
        }

    }

}