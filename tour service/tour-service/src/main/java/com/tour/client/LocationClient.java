package com.tour.client;

import com.tour.dto.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "location-service", url = "http://localhost:8082")
public interface LocationClient {

    @GetMapping("/api/locations/{id}")
    LocationResponse getLocationById(@PathVariable Long id);
}
