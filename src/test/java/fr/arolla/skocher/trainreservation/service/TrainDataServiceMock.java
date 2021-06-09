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
        trainStore.add(createTrain("express_2000", 0, 10,"A"));
        trainStore.add(createTrain("tgv_100", 7, 10,"A"));
        trainStore.add(createTrain("ter_north", 10, 100,"Blue"));
    }

    public Train getTrainById(String trainId) {
        return trainStore.stream()
            .filter(train -> train.getId().equals(trainId))
            .findFirst()
            .orElse(null);
    }

    private Train createTrain(String trainId, int numberOfSeatsBooked, int numberOfSeatsPerCoach, String... coaches) {
        List<Seat> seats = createTrainSeats(numberOfSeatsPerCoach, coaches);
        Train train = new Train(trainId, seats);

        bookTrainSeats(train, numberOfSeatsBooked);

        return train;
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
