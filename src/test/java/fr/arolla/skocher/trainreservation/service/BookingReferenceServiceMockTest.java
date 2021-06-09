package fr.arolla.skocher.trainreservation.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookingReferenceServiceMockTest {

    @Test
    public void should_get_a_not_empty_booking_id_from_service() {
        BookingReferenceService refService = new BookingReferenceServiceMock();

        String bookingId = refService.getNewBookingId();

        Assertions.assertThat(bookingId).isNotEmpty();
    }

}