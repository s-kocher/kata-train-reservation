package fr.arolla.skocher.trainreservation;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.arolla.skocher.trainreservation.service.TrainDataService;
import fr.arolla.skocher.trainreservation.service.TrainDataServiceMock;

public class TicketOfficeTest {

    @Test
    public void should_a_1_seat_reservation_in_an_empty_coach_be_approved() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

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
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

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

    @Test
    public void should_a_1_seat_reservation_in_a_more_than_70_booked_train_be_rejected() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("tgv_100", 1)
        );

        Reservation expectReservation = new Reservation(
            "tgv_100",
            List.of(),
            ""
        );
        Assertions.assertEquals(expectReservation, reservation);
    }

}