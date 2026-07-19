package com.tour.client;

import com.tour.dto.LodgingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "lodging-service", url = "http://localhost:8083")
public interface LodgingClient {

    @GetMapping("/api/lodgings/{id}")
    LodgingResponse getLodgingById(@PathVariable Long id);
}
