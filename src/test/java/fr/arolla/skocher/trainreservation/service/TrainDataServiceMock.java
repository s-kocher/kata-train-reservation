package fr.arolla.skocher.trainreservation.service;

import java.util.ArrayList;

import fr.arolla.skocher.trainreservation.Train;

public class TrainDataServiceMock implements TrainDataService {

    public TrainDataServiceMock() {
    }

    public Train getTrainById(String trainId) {
        return new Train("express_2000", new ArrayList<>());
    }

}
