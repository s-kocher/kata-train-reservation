package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrainTest {

    @Test
    public void should_seat_not_booked_be_free() {
        Seat seat = new Seat("A", 1);
        Train train = new Train(
            "express_2000",
            List.of( seat)
        );

        boolean isSeatFree = train.isSeatFree(seat);

        Assertions.assertTrue(isSeatFree);
    }

    @Test
    public void should_booked_seat_be_not_free() {
        Seat seat = new Seat("A", 1);
        Train train = new Train(
            "express_2000",
            List.of( seat)
        );

        train.book(seat);

        boolean isSeatFree = train.isSeatFree(seat);

        Assertions.assertFalse(isSeatFree);
    }

    @Test
    public void should_train_with_all_free_seats_be_0_percent_full() {
        Train train = new Train(
            "express_2000",
            List.of(
                new Seat("A", 1),
                new Seat("A", 2)
            )
        );

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(0, trainReservationRate);
    }

    @Test
    public void should_train_with_with_half_seats_reserved_be_50_percent_full() {
        Train train = new Train(
            "express_2000",
            List.of(
                new Seat("A", 1),
                new Seat("A", 2)
            )
        );
        train.book(train.seats.get(0));

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(50, trainReservationRate);
    }

    @Test
    public void should_train_of_10_seats_and_7_booked_be_70_percent_full() {
        List<Seat> seats = new ArrayList<>();
        for (int i=1; i<11; i++) {
            seats.add(new Seat("A", i));
        }
        Train train = new Train("express_2000", seats);

        for (int i=0; i<7; i++) {
            train.book(train.seats.get(i));
        }

        int trainReservationRate = train.getTrainReservationRate();

        Assertions.assertEquals(70, trainReservationRate);
    }

    @Test
    public void should_train_coach_with_all_free_seats_be_0_percent_full() {
        Train train = new Train(
            "express_2000",
            List.of(
                new Seat("A", 1),
                new Seat("A", 2)
            )
        );

        int coachReservationRate = train.getCoachReservationRate("A");

        Assertions.assertEquals(0, coachReservationRate);
    }

    @Test
    public void should_train_coach_with_half_free_seats_be_50_percent_full() {
        Train train = new Train(
            "express_2000",
            List.of(
                new Seat("A", 1),
                new Seat("A", 2)
            )
        );
        train.book(train.seats.get(0));

        int coachReservationRate = train.getCoachReservationRate("A");

        Assertions.assertEquals(50, coachReservationRate);
    }

}
