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

        List<Seat> bookedSeats = new ArrayList<>();

        Seat firstSeat = train.seats.get(0);
        int firstSeatNumber = firstSeat.seatNumber;
        String firstSeatCoach = firstSeat.coach;

        if (train.getCoachReservationRate(firstSeatCoach) >= 70) {
            bookedSeats.add(new Seat("B", 1));
        } else {
            for (int seatNumber = firstSeatNumber; seatNumber <= request.seatCount; seatNumber++) {
                bookedSeats.add(new Seat(firstSeatCoach, seatNumber));
            }
        }

        return new Reservation(request.trainId, bookedSeats, "75bcd15");
    }

}