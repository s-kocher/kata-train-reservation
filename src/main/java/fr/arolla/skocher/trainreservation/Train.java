package fr.arolla.skocher.trainreservation;

import java.util.List;

public class Train {

    String id;
    List<Seat> seats;

    public Train(String id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public int getTrainReservationRate() {
        return 0;
    }

    public boolean isSeatFree(Seat seat) {
        return true;
    }

}
