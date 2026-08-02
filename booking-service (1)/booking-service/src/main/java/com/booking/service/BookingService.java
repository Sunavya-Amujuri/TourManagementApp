package com.booking.service;

import com.booking.client.NotificationClient;
import com.booking.client.TourClient;
import com.booking.dto.*;
import com.booking.entity.Booking;
import com.booking.exception.BookingNotFoundException;
import com.booking.exception.InsufficientSeatsException;
import com.booking.mapper.BookingMapper;
import com.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TourClient tourClient;

    public BookingResponse createBooking(BookingRequest request) {

        // Get tour details from Tour Service
        TourResponse tour = tourClient.getTourById(request.getTourId());

        // Check seat availability
        if (request.getNumberOfPersons() > tour.getAvailableSeats()) {
            throw new InsufficientSeatsException("Not enough seats available");
        }

        // Reserve seats in Tour Service
        tourClient.reserveSeats(
                request.getTourId(),
                new SeatReservationRequest(request.getNumberOfPersons())
        );

        // Convert request to entity
        Booking booking = BookingMapper.toEntity(request);

        // Calculate total amount
        booking.setTotalAmount(tour.getPrice() * request.getNumberOfPersons());

        // Set booking status
        booking.setBookingStatus("PENDING");

        // Save booking
        Booking savedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(savedBooking);
    }

    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(BookingMapper::toResponse)
                .toList();
    }

    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        return BookingMapper.toResponse(booking);
    }

    public void deleteBooking(Long id) {

        bookingRepository.deleteById(id);
    }

    public void confirmBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        booking.setBookingStatus("CONFIRMED");

        bookingRepository.save(booking);
    }
}
