package com.booking.mapper;

import com.booking.dto.BookingRequest;
import com.booking.dto.BookingResponse;
import com.booking.entity.Booking;

public class BookingMapper {
    public static Booking toEntity(BookingRequest request) {
        return Booking.builder()
                .userId(request.getUserId())
                .tourId(request.getTourId())
                .userEmail(request.getUserEmail())
                .bookingDate(request.getBookingDate())
                .numberOfPersons(request.getNumberOfPersons())
                .build();
    }

    public static BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .userId(booking.getUserId())
                .tourId(booking.getTourId())
                .userEmail(booking.getUserEmail())   // ADD THIS
                .bookingDate(booking.getBookingDate())
                .numberOfPersons(booking.getNumberOfPersons())
                .totalAmount(booking.getTotalAmount())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }
}
