package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrainTest {

    @Test
    public void should_seat_not_booked_be_free() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(1, "A")
        );
        Seat seat = train.seats.get(0);

        boolean isSeatFree = train.isSeatFree(seat);

        Assertions.assertTrue(isSeatFree);
    }

    @Test
    public void should_booked_seat_be_not_free() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(1, "A")
        );
        Seat seat = train.seats.get(0);
        train.book(seat);

        boolean isSeatFree = train.isSeatFree(seat);

        Assertions.assertFalse(isSeatFree);
    }

    @Test
    public void should_train_with_all_free_seats_be_0_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(2, "A")
        );

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(0, trainReservationRate);
    }

    @Test
    public void should_train_with_with_half_seats_reserved_be_50_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(2, "A")
        );
        bookTrainSeats(train, 1);

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(50, trainReservationRate);
    }

    @Test
    public void should_train_of_10_seats_and_7_booked_be_70_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(10, "A")
        );
        bookTrainSeats(train, 7);

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(70, trainReservationRate);
    }

    @Test
    public void should_train_coach_with_all_free_seats_be_0_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(2, "A")
        );

        int coachReservationRate = train.getCoachReservationRate("A");

        Assertions.assertEquals(0, coachReservationRate);
    }

    @Test
    public void should_train_coach_with_half_free_seats_be_50_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(2, "A")
        );
        bookTrainSeats(train, 1);

        int coachReservationRate = train.getCoachReservationRate("A");

        Assertions.assertEquals(50, coachReservationRate);
    }

    @Test
    public void should_train_coach_of_10_seats_and_7_booked_be_70_percent_full() {
        Train train = new Train(
            "express_2000",
            createTrainSeats(10, "A")
        );
        bookTrainSeats(train, 7);

        int coachReservationRate = train.getCoachReservationRate("A");

        Assertions.assertEquals(70, coachReservationRate);
    }

    private void bookTrainSeats(Train train, int numberOfSeats) {
        for (int i=0; i<numberOfSeats; i++) {
            train.book(train.seats.get(i));
        }
    }

    private List<Seat> createTrainSeats(int numberOfSeatsPerCoach, String... coaches) {
        List<Seat> seats = new ArrayList<>();
        for (String coach : coaches) {
            for (int seatNumber = 1; seatNumber <=numberOfSeatsPerCoach; seatNumber++) {
                seats.add(new Seat(coach, seatNumber));
            }
        }

        return seats;
    }

}
