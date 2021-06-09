package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;

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
        return 0;
    }
}
