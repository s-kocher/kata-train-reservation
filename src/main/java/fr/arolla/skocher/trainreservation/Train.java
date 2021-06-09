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

    public void book(int seatIndex) {
        bookedSeats.add(seats.get(seatIndex));
    }

    public int getTrainReservationRate() {
        return getBookedSeatRate(bookedSeats, seats, 0);
    }

    public int getCoachReservationRateWithNewBookSeats(String coach, int newBookedSeats) {
        List<Seat> bookedCoachSeats = getCoachSeats(bookedSeats, coach);
        List<Seat> coachSeats = getCoachSeats(seats, coach);

        return getBookedSeatRate(bookedCoachSeats, coachSeats, newBookedSeats);
    }
    public int getCoachReservationRate(String coach) {
        return getCoachReservationRateWithNewBookSeats(coach, 0);
    }

    private List<Seat> getCoachSeats(List<Seat> seats, String coach) {
        return seats.stream()
            .filter(seat -> seat.coach.equals(coach))
            .collect(Collectors.toList());
    }

    private int getBookedSeatRate(List<Seat> bookedSeats,  List<Seat> seats, int newBookedSeats) {
        return (bookedSeats.size() + newBookedSeats) * 100 / seats.size();
    }

}
