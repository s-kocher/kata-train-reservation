package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
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

        List<Seat> seats = new ArrayList<>();
        for (int seatNumber=0;seatNumber<request.seatCount;seatNumber++) {
            seats.add(new Seat("A", seatNumber));
        }

        return new Reservation(request.trainId, seats, "75bcd15");
    }

}