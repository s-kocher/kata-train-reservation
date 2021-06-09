package fr.arolla.skocher.trainreservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.arolla.skocher.trainreservation.Seat;
import fr.arolla.skocher.trainreservation.Train;

public class TrainDataServiceMock implements TrainDataService {

    Set<Train> trainStore = new HashSet<>();

    public TrainDataServiceMock() {
        //declare all needed trains for tests with different trains id for each situations
        //trains ids will be like persona
        Train express2000 = new Train(
            "express_2000",
            createTrainSeats(10, "A")
        );
        bookTrainSeats(express2000, 7);
        trainStore.add(express2000);

        Train tgv100 = new Train(
            "tgv_100",
            createTrainSeats(10, "A")
        );
        trainStore.add(tgv100);

    }

    public Train getTrainById(String trainId) {
        return trainStore.stream()
            .filter(train -> train.getId().equals(trainId))
            .findFirst()
            .orElse(null);
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

    private void bookTrainSeats(Train train, int numberOfSeats) {
        for (int i=0; i<numberOfSeats; i++) {
            train.book(i);
        }
    }

}
