package com.tour.client;

import com.tour.config.FeignConfig;
import com.tour.dto.TransportResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "transport-service",
        configuration = FeignConfig.class
)
public interface TransportClient {

    @GetMapping("/api/transports/{id}")
    TransportResponse getTransportById(@PathVariable Long id);
}
