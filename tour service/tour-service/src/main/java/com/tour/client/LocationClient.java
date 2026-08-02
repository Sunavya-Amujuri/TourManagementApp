package com.tour.client;

import com.tour.config.FeignConfig;
import com.tour.dto.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "location-service",
        configuration = FeignConfig.class
)
public interface LocationClient {

    @GetMapping("/api/locations/{id}")
    LocationResponse getLocationById(@PathVariable Long id);
}
