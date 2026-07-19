package com.location.service;

import com.location.dto.LocationRequest;
import com.location.dto.LocationResponse;
import com.location.entity.Location;
import com.location.exception.LocationNotFoundException;
import com.location.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private static final Logger logger = Logger.getLogger(LocationService.class.getName());

    @Autowired
    private LocationRepo locationRepo;

    // create Location
    public LocationResponse createLocation(LocationRequest request) {
        logger.info("Creating new location");

        Location location = Location.builder()
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .description(request.getDescription())
                .build();

        Location saved = locationRepo.save(location);

        logger.info("Created location with id=" + saved.getLocationId());

        return LocationResponse.builder()
                .locationId(saved.getLocationId())
                .city(saved.getCity())
                .state(saved.getState())
                .country(saved.getCountry())
                .description(saved.getDescription())
                .build();

//        Location saved = locationRepo.save(location);
//        logger.info("Created location with id=" + saved.getLocationId());
//        return mapToResponse(saved);
    }

    // Get location by id
    public LocationResponse getLocationById(Long id) {
        logger.info("Fetching location with ID : " + id);

        Location location = locationRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + id));

        return LocationResponse.builder()
                .locationId(location.getLocationId())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .description(location.getDescription())
                .build();
    }

    // Get All Locations
    public List<LocationResponse> getAllLocations() {
        logger.info("Fetching all locations");

        return locationRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // update location
    public LocationResponse updateLocation(Long id, LocationRequest request) {
        logger.info("Updating location with ID : " + id);

        Location location = locationRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with ID : " + id));

        location.setCity(request.getCity());
        location.setState(request.getState());
        location.setCountry(request.getCountry());
        location.setDescription(request.getDescription());

        Location updatedLocation = locationRepo.save(location);

        logger.info("Location updated successfully.");

        return mapToResponse(updatedLocation);
    }

    // delete the location
    public String deleteLocation(Long id) {
        logger.info("Deleting location with ID : " + id);

        Location location = locationRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with ID : " + id));

        locationRepo.delete(location);

        logger.info("Location deleted successfully.");

        return "Location deleted successfully.";
    }

    // Entity -> Response DTO
    private LocationResponse mapToResponse(Location loc) {
        return LocationResponse.builder()
                .locationId(loc.getLocationId())
                .city(loc.getCity())
                .state(loc.getState())
                .country(loc.getCountry())
                .description(loc.getDescription())
                .build();
    }
}

