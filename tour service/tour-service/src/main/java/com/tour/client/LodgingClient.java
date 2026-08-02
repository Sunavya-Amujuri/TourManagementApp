package com.tour.client;

import com.tour.config.FeignConfig;
import com.tour.dto.LodgingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "lodging-service",
        configuration = FeignConfig.class
)
public interface LodgingClient {

    @GetMapping("/api/lodgings/{id}")
    LodgingResponse getLodgingById(@PathVariable Long id);
}
