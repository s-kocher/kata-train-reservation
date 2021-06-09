package fr.arolla.skocher.trainreservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Train {

    String id;
    List<Seat> seats;

    List<Seat> bookedSeats = new ArrayList<>();

    public Train(String id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public boolean isSeatFree(Seat seat) {
        return !bookedSeats.contains(seat);
    }

    public void book(Seat seat) {
        bookedSeats.add(seat);
    }

    public int getTrainReservationRate() {
        return getBookedSeatRate(bookedSeats, seats);
    }

    public int getCoachReservationRate(String coach) {
        List<Seat> bookedCoachSeats = getCoachSeats(bookedSeats, coach);
        List<Seat> coachSeats = getCoachSeats(seats, coach);

        return getBookedSeatRate(bookedCoachSeats, coachSeats);
    }

    private List<Seat> getCoachSeats(List<Seat> seats, String coach) {
        return seats.stream()
            .filter(seat -> seat.coach.equals(coach))
            .collect(Collectors.toList());
    }

    private int getBookedSeatRate(List<Seat> bookedSeats,  List<Seat> seats) {
        return bookedSeats.size() * 100 / seats.size();
    }

}
