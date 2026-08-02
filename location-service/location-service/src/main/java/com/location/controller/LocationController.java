package com.location.controller;

import com.location.dto.LocationRequest;
import com.location.dto.LocationResponse;
import com.location.service.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Create Location
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public LocationResponse createLocation(@RequestBody LocationRequest request) {
        return locationService.createLocation(request);
    }

    // Get All Locations
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<LocationResponse> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Get Location By ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public LocationResponse getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    // Update Location
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public LocationResponse updateLocation(@PathVariable Long id,
                                           @RequestBody LocationRequest request) {
        return locationService.updateLocation(id, request);
    }

    // Delete Location
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }
}
