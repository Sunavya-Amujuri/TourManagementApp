package com.tour.service;

import com.tour.client.LocationClient;
import com.tour.dto.LocationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceHelper {
    private final LocationClient locationClient;

    @Retry(name = "locationService", fallbackMethod = "locationFallback")
    public LocationResponse getLocation(Long locationId) {
        System.out.println("Calling Location Service...");
        return locationClient.getLocationById(locationId);
    }

    public LocationResponse locationFallback(Long locationId, Exception ex) {

        System.out.println("Location fallback executed!");

        LocationResponse response = new LocationResponse();

        response.setLocationId(locationId);
        response.setCity("Service Unavailable");
        response.setState("");
        response.setCountry("");
        response.setDescription("Location Service is temporarily unavailable.");

        return response;
    }
}
