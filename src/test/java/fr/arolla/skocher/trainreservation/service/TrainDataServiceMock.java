package fr.arolla.skocher.trainreservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.arolla.skocher.trainreservation.Train;

public class TrainDataServiceMock implements TrainDataService {

    Set<Train> trainStore = new HashSet<>();

    public TrainDataServiceMock() {
        //declare all needed trains for tests with different trains id for each situations
        //trains ids will be like persona
        trainStore.add(new Train("express_2000",new ArrayList<>()));
        trainStore.add(new Train("tgv_100",new ArrayList<>()));

    }

    public Train getTrainById(String trainId) {
        return trainStore.stream()
            .filter(train -> train.getId().equals(trainId))
            .findFirst()
            .orElse(null);
    }

}
