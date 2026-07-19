package com.location.controller;

import com.location.dto.LocationRequest;
import com.location.dto.LocationResponse;
import com.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Create Location
    @PostMapping
    public LocationResponse createLocation(@RequestBody LocationRequest request) {
        return locationService.createLocation(request);
    }

    // Get All Locations
    @GetMapping
    public List<LocationResponse> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Get Location By ID
    @GetMapping("/{id}")
    public LocationResponse getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    // Update Location
    @PutMapping("/{id}")
    public LocationResponse updateLocation(@PathVariable Long id,
                                           @RequestBody LocationRequest request) {
        return locationService.updateLocation(id, request);
    }

    // Delete Location
    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }
}
