package fr.arolla.skocher.trainreservation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

        isReservationApprovedForRightTrain(reservation, "express_2000", 1);
    }

    @Test
    public void should_a2_seats_reservation_in_an_empty_coach_be_approved() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("express_2000", 2)
        );

        isReservationApprovedForRightTrain(reservation, "express_2000", 2);
    }

    @Test
    public void should_a_1_seat_reservation_in_a_more_than_70_booked_train_be_rejected() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("tgv_100", 1)
        );

        isReservationRejectedForRightTrain(reservation, "tgv_100");
    }

    @Test
    public void should_a_5_seat_reservation_in_a_less_than_70_booked_train_be_authorized() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_north", 5)
        );

        isReservationApprovedForRightTrain(reservation, "ter_north", 5);
    }

    @Test
    public void should_approved_reservation_use_existing_train_seats() {
        TrainDataService tds = new TrainDataServiceMock();
        TicketOffice ticketOffice = new TicketOffice(tds);

        Train train = tds.getTrainById("ter_north");

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_north", 2)
        );

        Assertions.assertThat(train.seats).containsAll(reservation.seats);
    }

    @Test
    public void should_approved_reservation_in_partially_booked_coach_but_still_allowed_seat_use_the_right_seats_numbers() {
        TrainDataService tds = new TrainDataServiceMock();
        TicketOffice ticketOffice = new TicketOffice(tds);

        Train train = tds.getTrainById("ter_north");

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_north", 2)
        );

        Assertions.assertThat(reservation.seats.size()).isEqualTo(2);
        Assertions.assertThat(reservation.seats.get(0).seatNumber).isEqualTo(11);
        Assertions.assertThat(reservation.seats.get(1).seatNumber).isEqualTo(12);
    }

    @Test
    public void should_a_1_seat_reservation_in_a_2_coaches_train_where_first_coach_is_full_be_authorized_in_second_coach() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_south", 1)
        );

        Assertions.assertThat(reservation.seats.get(0))
            .isEqualTo(new Seat("B", 1)
        );
    }

    @Test
    public void should_a_1_seat_reservation_in_a_2_coaches_train_where_first_and_second_coaches_are_full_be_authorized_in_third_coach() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_west", 1)
        );

        Assertions.assertThat(reservation.seats.get(0))
            .isEqualTo(new Seat("Red", 1)
            );
    }

    @Test
    public void should_reservation_with_many_seats_be_in_second_coach_to_not_exceed_the_reservation_limit_for_first_coach() {
        TicketOffice ticketOffice = new TicketOffice(new TrainDataServiceMock());

        //ter_est train has 2 coaches of 205 seats, at start, 13 seats already booked,
        // Start situation : train is 32% booked, and first coach is 65%
        // Booking evaluation :
        // - 2 seats are allowed since global train reservations will be 37% after
        // - first coach cannot be used since after booking 2 other seats inside it, its reservation will be 75%
        Reservation reservation = ticketOffice.makeReservation(
            new ReservationRequest("ter_est", 2)
        );

        isReservationApprovedForRightTrain(reservation, "ter_est", 2);
        Assertions.assertThat(reservation.seats.get(0))
            .isEqualTo(new Seat("B", 1)
        );
        Assertions.assertThat(reservation.seats.get(1))
            .isEqualTo(new Seat("B", 2)
        );
    }

    public void isReservationApprovedForRightTrain(Reservation reservation, String trainId, int numberOfSeatsExpected) {
        Assertions.assertThat(reservation.trainId).isEqualTo(trainId);
        Assertions.assertThat(reservation.seats.size()).isEqualTo(numberOfSeatsExpected);
        Assertions.assertThat(reservation.bookingId).isNotEmpty();
    }

    public void isReservationRejectedForRightTrain(Reservation reservation, String trainId) {
        Assertions.assertThat(reservation.trainId).isEqualTo(trainId);
        Assertions.assertThat(reservation.seats.size()).isEqualTo(0);
        Assertions.assertThat(reservation.bookingId).isEmpty();
    }

}