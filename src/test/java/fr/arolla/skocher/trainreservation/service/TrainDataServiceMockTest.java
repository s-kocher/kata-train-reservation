package fr.arolla.skocher.trainreservation.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.arolla.skocher.trainreservation.Train;

class TrainDataServiceMockTest {

    @Test
    public void should_retrieve_express_2000_train() {
        TrainDataService trainDataService = new TrainDataServiceMock();

        Train train = trainDataService.getTrainById("express_2000");

        Assertions.assertNotNull(train);
        Assertions.assertEquals("express_2000", train.getId());

    }

}