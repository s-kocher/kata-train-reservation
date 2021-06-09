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

    public int getTrainReservationRate() {
        int totalSeats = seats.size();
        int totalBookedSeats = bookedSeats.size();
        return totalBookedSeats * 100 / totalSeats;
    }

    public boolean isSeatFree(Seat seat) {
        return !bookedSeats.contains(seat);
    }

    public void book(Seat seat) {
        bookedSeats.add(seat);
    }

    public int getCoachReservationRate(String coach) {
        List<Seat> bookedCoachSeats = getCoachSeats(bookedSeats, coach);
        List<Seat> coachSeats = getCoachSeats(seats, coach);

        int totalSeats = coachSeats.size();
        int totalBookedSeats = bookedCoachSeats.size();
        return totalBookedSeats * 100 / totalSeats;
    }

    private List<Seat> getCoachSeats(List<Seat> seats, String coach) {
        return seats.stream()
            .filter(seat -> seat.coach.equals(coach))
            .collect(Collectors.toList());
    }

}
