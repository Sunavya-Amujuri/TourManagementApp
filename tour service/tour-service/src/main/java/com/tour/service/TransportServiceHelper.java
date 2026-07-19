package com.tour.service;

import com.tour.client.TransportClient;
import com.tour.dto.TransportResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportServiceHelper {
    private final TransportClient transportClient;

    @Retry(name = "transportService", fallbackMethod = "transportFallback")
    public TransportResponse getTransport(Long transportId) {

        System.out.println("Calling Transport Service...");

        return transportClient.getTransportById(transportId);
    }

    public TransportResponse transportFallback(Long transportId, Exception ex) {

        System.out.println("Transport fallback executed!");

        TransportResponse response = new TransportResponse();

        response.setTransportId(transportId);
        response.setTransportType("Service Unavailable");
        response.setProviderName("");
        response.setSource("");
        response.setDestination("");
        response.setPrice(0.0);
        response.setAvailableSeats(0);
        response.setDescription("Transport Service is temporarily unavailable.");

        return response;
    }
}
