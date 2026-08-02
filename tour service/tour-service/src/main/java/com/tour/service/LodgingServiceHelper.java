package com.tour.service;

import com.tour.client.LodgingClient;
import com.tour.dto.LodgingResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LodgingServiceHelper {
    private final LodgingClient lodgingClient;

    @Retry(name = "lodgingService", fallbackMethod = "lodgingFallback")
    public LodgingResponse getLodging(Long lodgingId) {
        System.out.println("Calling Lodging Service...");
        return lodgingClient.getLodgingById(lodgingId);
    }

    public LodgingResponse lodgingFallback(Long lodgingId, Exception ex) {

        System.out.println("Lodging fallback executed!");

        System.out.println("Reason: " + ex.getMessage());
        ex.printStackTrace();

        LodgingResponse response = new LodgingResponse();

        response.setLodgingId(lodgingId);
        response.setHotelName("Service Unavailable");
        response.setAddress("");
        response.setCity("");
        response.setPricePerNight(0.0);
        response.setRating(0.0);
        response.setDescription("Lodging Service is temporarily unavailable.");
        response.setAvailableRooms(0);

        return response;
    }
}
