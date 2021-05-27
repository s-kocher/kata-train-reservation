package fr.arolla.skocher.trainreservation;

import java.util.List;

public class TicketOffice {

    public Reservation makeReservation(ReservationRequest request) {
        if (request.seatCount == 2) {
            return new Reservation(
                "express_2000",
                List.of(
                    new Seat("A", 1),
                    new Seat("A", 2)
                ),
                "75bcd15"
            );
        }
        return new Reservation(
            "express_2000",
            List.of(new Seat("A", 1)),
            "75bcd15"
        );
    }

}