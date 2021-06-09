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
        Seat firstSeat = train.seats.get(0);
        int firstSeatNumber = firstSeat.seatNumber;
        String firstSeatCoach = firstSeat.coach;
        for (int seatNumber=firstSeatNumber;seatNumber<=request.seatCount;seatNumber++) {
            seats.add(new Seat(firstSeatCoach, seatNumber));
        }

        return new Reservation(request.trainId, seats, "75bcd15");
    }

}