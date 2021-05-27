package fr.arolla.skocher.trainreservation;

public class Seat {

    public final String coach;
    public final int seatNumber;

    private String bookingReference = "";

    public Seat(String coach, int seatNumber) {
        this.coach = coach;
        this.seatNumber = seatNumber;
    }

    public boolean equals(Object o) {
        Seat other = (Seat)o;
        return coach == other.coach && seatNumber == other.seatNumber;
    }

    @Override
    public String toString() {
        return "Seat{" +
            "coach='" + coach + '\'' +
            ", seatNumber=" + seatNumber +
            ", bookingReference='" + bookingReference + '\'' +
            '}';
    }

    public boolean isFree() {
        return bookingReference.isEmpty();
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

}