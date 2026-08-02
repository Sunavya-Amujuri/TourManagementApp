package com.booking.client;

import com.booking.config.FeignConfig;
import com.booking.dto.SeatReservationRequest;
import com.booking.dto.TourResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "TOUR-SERVICE",
        configuration = FeignConfig.class
)
public interface TourClient {
    @GetMapping("/api/tours/{id}")
    TourResponse getTourById(@PathVariable Long id);

    @PutMapping("/api/tours/{id}/reserve-seats")
    String reserveSeats(@PathVariable Long id,
                        @RequestBody SeatReservationRequest request);
}
