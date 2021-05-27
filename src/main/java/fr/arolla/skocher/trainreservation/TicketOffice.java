package fr.arolla.skocher.trainreservation;

import java.util.List;

public class TicketOffice {

    public Reservation makeReservation(ReservationRequest request) {
        return new Reservation(
            "express_2000",
            List.of(new Seat("A", 1)),
            "75bcd15"
        );
    }

}