package fr.arolla.skocher.trainreservation;

import org.assertj.core.api.Assertions;
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

        Assertions.assertThat(reservation.trainId).isEqualTo("express_2000");
        Assertions.assertThat(reservation.seats.size()).isEqualTo(1);
        Assertions.assertThat(reservation.bookingId).isNotEmpty();
    }

    @Test
    public void should_a2_seats_reservation_in_an_empty_coach_be_approved() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("express_2000", 2)
        );

        Assertions.assertThat(reservation.trainId).isEqualTo("express_2000");
        Assertions.assertThat(reservation.seats.size()).isEqualTo(2);
        Assertions.assertThat(reservation.bookingId).isNotEmpty();
    }

    @Test
    public void should_a_1_seat_reservation_in_a_more_than_70_booked_train_be_rejected() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("tgv_100", 1)
        );

        Assertions.assertThat(reservation.trainId).isEqualTo("tgv_100");
        Assertions.assertThat(reservation.seats.size()).isEqualTo(0);
        Assertions.assertThat(reservation.bookingId).isEmpty();
    }

}