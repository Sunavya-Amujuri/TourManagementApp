package com.tour.controller;

import com.tour.dto.SeatReservationRequest;
import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;
import com.tour.service.TourService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TourController {

    private final TourService tourService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TourResponse> createTour(@Valid @RequestBody TourRequest request) {
        TourResponse response = tourService.createTour(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<TourResponse>> getAllTours() {
        List<TourResponse> tours = tourService.getAllTours();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<TourResponse> getTourById(@PathVariable Long id) {
        TourResponse tour = tourService.getTourById(id);
        return ResponseEntity.ok(tour);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TourResponse> updateTour(
            @PathVariable Long id,
            @Valid @RequestBody TourRequest request) {
        TourResponse response = tourService.updateTour(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return ResponseEntity.ok("Tour deleted successfully.");
    }

    @PutMapping("/{id}/reserve-seats")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> reserveSeats(
            @PathVariable Long id,
            @RequestBody SeatReservationRequest request) {

        tourService.reserveSeats(id, request.getNumberOfSeats());

        return ResponseEntity.ok("Seats reserved successfully");
    }
}
