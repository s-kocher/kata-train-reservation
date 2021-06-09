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
        if (bookedSeat.size() > 0) {
            return 50;
        }
        return 0;
    }

    public boolean isSeatFree(Seat seat) {
        return !bookedSeat.contains(seat);
    }

    public void book(Seat seat) {
        bookedSeat.add(seat);
    }
}
