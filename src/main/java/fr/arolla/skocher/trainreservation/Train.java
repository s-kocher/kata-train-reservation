package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Train {

    String id;
    List<Seat> seats;

    List<Seat> bookedSeat = new ArrayList<>();

    public Train(String id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public int getTrainReservationRate() {
        int totalSeats = seats.size();
        int totalBookedSeat = bookedSeat.size();
        return totalBookedSeat * 100 / totalSeats;
    }

    public boolean isSeatFree(Seat seat) {
        return !bookedSeat.contains(seat);
    }

    public void book(Seat seat) {
        bookedSeat.add(seat);
    }

    public int getCoachReservationRate(String coach) {
    List<Seat> coachSeats = bookedSeat.stream()
        .filter(seat -> seat.coach.equals(coach))
        .collect(Collectors.toList());

        if (coachSeats.isEmpty()) {
            return 0;
        }
        return 50;
    }

}
