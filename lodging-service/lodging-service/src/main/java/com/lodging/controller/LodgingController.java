package com.lodging.controller;

import com.lodging.dto.LodgingRequest;
import com.lodging.dto.LodgingResponse;
import com.lodging.service.LodgingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lodgings")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LodgingController {
    @Autowired
    private LodgingService lodgingService;

    // Create Lodging
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public LodgingResponse createLodging(@Valid @RequestBody LodgingRequest request) {
        return lodgingService.createLodging(request);
    }

    // Get All Lodgings
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<LodgingResponse> getAllLodgings() {
        return lodgingService.getAllLodgings();
    }

    // Get Lodging By ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public LodgingResponse getLodgingById(@PathVariable Long id) {
        return lodgingService.getLodgingById(id);
    }

    // Update Lodging
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public LodgingResponse updateLodging(@PathVariable Long id,
                                         @Valid @RequestBody LodgingRequest request) {
        return lodgingService.updateLodging(id, request);
    }

    // Delete Lodging
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteLodging(@PathVariable Long id) {
        return lodgingService.deleteLodging(id);
    }
}
