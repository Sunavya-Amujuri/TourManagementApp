package com.payment.client;

import com.payment.dto.BookingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingClient {
    @GetMapping("/bookings/{id}")
    BookingResponse getBookingById(@PathVariable Long id);

    @PutMapping("/bookings/{id}/confirm")
    String confirmBooking(@PathVariable Long id);
}
