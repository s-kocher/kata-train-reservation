package fr.arolla.skocher.trainreservation;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrainTest {

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

}