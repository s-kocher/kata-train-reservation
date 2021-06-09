package fr.arolla.skocher.trainreservation.service;

import fr.arolla.skocher.trainreservation.Train;

public interface TrainDataService {

    Train getTrainById(String trainId);

}
