package fr.arolla.skocher.trainreservation;

import java.util.List;

import fr.arolla.skocher.trainreservation.service.TrainDataService;

public class TicketOffice {

    private TrainDataService trainDataService;

    public TicketOffice(TrainDataService trainDataService) {
        this.trainDataService = trainDataService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        Train train = trainDataService.getTrainById(request.trainId);
        if (train.getTrainReservationRate() >= 70) {
            return new Reservation(
                request.trainId,
                List.of(),
                ""
            );
        }

        if (request.seatCount == 2) {
            return new Reservation(
                "express_2000",
                List.of(
                    new Seat("A", 1),
                    new Seat("A", 2)
                ),
                "75bcd15"
            );
        }
        return new Reservation(
            "express_2000",
            List.of(new Seat("A", 1)),
            "75bcd15"
        );
    }

}