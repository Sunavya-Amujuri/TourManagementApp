package com.lodging.controller;

import com.lodging.dto.LodgingRequest;
import com.lodging.dto.LodgingResponse;
import com.lodging.service.LodgingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lodgings")
public class LodgingController {
    @Autowired
    private LodgingService lodgingService;

    // Create Lodging
    @PostMapping
    public LodgingResponse createLodging(@Valid @RequestBody LodgingRequest request) {
        return lodgingService.createLodging(request);
    }

    // Get All Lodgings
    @GetMapping
    public List<LodgingResponse> getAllLodgings() {
        return lodgingService.getAllLodgings();
    }

    // Get Lodging By ID
    @GetMapping("/{id}")
    public LodgingResponse getLodgingById(@PathVariable Long id) {
        return lodgingService.getLodgingById(id);
    }

    // Update Lodging
    @PutMapping("/{id}")
    public LodgingResponse updateLodging(@PathVariable Long id,
                                         @Valid @RequestBody LodgingRequest request) {
        return lodgingService.updateLodging(id, request);
    }

    // Delete Lodging
    @DeleteMapping("/{id}")
    public String deleteLodging(@PathVariable Long id) {
        return lodgingService.deleteLodging(id);
    }
}
