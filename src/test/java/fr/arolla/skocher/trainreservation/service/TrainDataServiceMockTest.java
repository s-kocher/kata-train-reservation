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

    @Test
    public void should_retrieve_tgv_100_train() {
        TrainDataService trainDataService = new TrainDataServiceMock();

        Train train = trainDataService.getTrainById("tgv_100");

        Assertions.assertNotNull(train);
        Assertions.assertEquals("tgv_100", train.getId());
    }

    @Test
    public void should_retrieve_tgv_100_train_with_global_70_percent_reservation() {
        TrainDataService trainDataService = new TrainDataServiceMock();

        Train train = trainDataService.getTrainById("tgv_100");

        Assertions.assertEquals(70, train.getTrainReservationRate());
    }

    @Test
    public void should_retrieve_express_2000_train_with_0_percent_reservation() {
        TrainDataService trainDataService = new TrainDataServiceMock();

        Train train = trainDataService.getTrainById("express_2000");

        Assertions.assertEquals(0, train.getTrainReservationRate());
    }

}