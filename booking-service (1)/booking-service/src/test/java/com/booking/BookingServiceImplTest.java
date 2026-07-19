package com.booking;

import com.booking.client.NotificationClient;
import com.booking.client.TourClient;
import com.booking.dto.*;
import com.booking.entity.Booking;
import com.booking.repository.BookingRepository;
import com.booking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TourClient tourClient;

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void contextLoads() {
        // ---------- Arrange ----------

        BookingRequest request = new BookingRequest();
        request.setUserId(1L);
        request.setTourId(1L);
        request.setBookingDate(LocalDate.now());
        request.setNumberOfPersons(2);

        TourResponse tour = new TourResponse();
        tour.setId(1L);
        tour.setTourName("Hyderabad Heritage Tour");
        tour.setPrice(15000.0);
        tour.setAvailableSeats(10);

        Booking savedBooking = new Booking();
        savedBooking.setBookingId(1L);
        savedBooking.setUserId(1L);
        savedBooking.setTourId(1L);
        savedBooking.setBookingDate(LocalDate.now());
        savedBooking.setNumberOfPersons(2);
        savedBooking.setTotalAmount(30000.0);
        savedBooking.setBookingStatus("PENDING");

        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(1L);
        notificationResponse.setRecipient("user@gmail.com");
        notificationResponse.setStatus("SENT");
        notificationResponse.setSentAt(LocalDateTime.now());

        when(tourClient.getTourById(1L))
                .thenReturn(tour);

        when(tourClient.reserveSeats(eq(1L), any(SeatReservationRequest.class)))
                .thenReturn("Seats reserved successfully");

        when(bookingRepository.save(any(Booking.class)))
                .thenReturn(savedBooking);

        when(notificationClient.sendNotification(any(NotificationRequest.class)))
                .thenReturn(notificationResponse);

        // ---------- Act ----------

        BookingResponse response = bookingService.createBooking(request);

        // ---------- Assert ----------

        assertNotNull(response);
        assertEquals(1L, response.getBookingId());
        assertEquals(1L, response.getUserId());
        assertEquals(1L, response.getTourId());
        assertEquals(30000.0, response.getTotalAmount());
        assertEquals("PENDING", response.getBookingStatus());

        verify(tourClient).getTourById(1L);

        verify(tourClient)
                .reserveSeats(eq(1L), any(SeatReservationRequest.class));

        verify(bookingRepository)
                .save(any(Booking.class));

        verify(notificationClient)
                .sendNotification(any(NotificationRequest.class));
    }
}
