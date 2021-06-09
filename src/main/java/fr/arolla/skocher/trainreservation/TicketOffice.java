package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;

import fr.arolla.skocher.trainreservation.service.TrainDataService;

public class TicketOffice {

    public static final int MAX_ALLOWED_RESERVATION_RATE = 70;
    private TrainDataService trainDataService;

    public TicketOffice(TrainDataService trainDataService) {
        this.trainDataService = trainDataService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        Train train = trainDataService.getTrainById(request.trainId);
        if (train.getTrainReservationRate() >= MAX_ALLOWED_RESERVATION_RATE) {
            return getEmptyReservation(request.trainId);
        }

        List<Seat> bookedSeats = new ArrayList<>();

        Seat firstBookedSeat = null;
        for (Seat seat : train.seats) {
            int coachReservationRate = train.getCoachReservationRate(seat.coach);
            if (coachReservationRate < 70) {
                firstBookedSeat = seat;
                break;
            }
        }

        if (firstBookedSeat == null) {
            return getEmptyReservation(request.trainId);
        }

        int firstBookedSeatNumber = firstBookedSeat.seatNumber;
        String firstBookedSeatCoach = firstBookedSeat.coach;

        for (int seatNumber = firstBookedSeatNumber; seatNumber <= request.seatCount; seatNumber++) {
            bookedSeats.add(new Seat(firstBookedSeatCoach, seatNumber));
        }

        return new Reservation(request.trainId, bookedSeats, "75bcd15");
    }

    private Reservation getEmptyReservation(String trainId) {
        return new Reservation(trainId, new ArrayList<>(), "");
    }

}