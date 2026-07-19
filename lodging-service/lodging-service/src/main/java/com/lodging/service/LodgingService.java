package com.lodging.service;

import com.lodging.dto.LodgingRequest;
import com.lodging.dto.LodgingResponse;
import com.lodging.entity.Lodging;
import com.lodging.exception.ResourceNotFoundException;
import com.lodging.repository.LodgingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LodgingService {
    @Autowired
    private LodgingRepository lodgingRepository;

    // Create Lodging
    public LodgingResponse createLodging(LodgingRequest request) {

        Lodging lodging = Lodging.builder()
                .hotelName(request.getHotelName())
                .address(request.getAddress())
                .city(request.getCity())
                .pricePerNight(request.getPricePerNight())
                .rating(request.getRating())
                .description(request.getDescription())
                .availableRooms(request.getAvailableRooms())
                .build();

        lodging = lodgingRepository.save(lodging);

        return mapToResponse(lodging);
    }

    // Get All Lodgings
    public List<LodgingResponse> getAllLodgings() {
        return lodgingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Lodging By Id
    public LodgingResponse getLodgingById(Long id) {

        Lodging lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lodging not found with id : " + id));

        return mapToResponse(lodging);
    }

    // Update Lodging
    public LodgingResponse updateLodging(Long id, LodgingRequest request) {

        Lodging lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lodging not found with id : " + id));

        lodging.setHotelName(request.getHotelName());
        lodging.setAddress(request.getAddress());
        lodging.setCity(request.getCity());
        lodging.setPricePerNight(request.getPricePerNight());
        lodging.setRating(request.getRating());
        lodging.setDescription(request.getDescription());
        lodging.setAvailableRooms(request.getAvailableRooms());

        lodging = lodgingRepository.save(lodging);

        return mapToResponse(lodging);
    }

    // Delete Lodging
    public String deleteLodging(Long id) {

        Lodging lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lodging not found with id : " + id));

        lodgingRepository.delete(lodging);

        return "Lodging deleted successfully.";
    }

    // Convert Entity to Response DTO
    private LodgingResponse mapToResponse(Lodging lodging) {

        return LodgingResponse.builder()
                .lodgingId(lodging.getLodgingId())
                .hotelName(lodging.getHotelName())
                .address(lodging.getAddress())
                .city(lodging.getCity())
                .pricePerNight(lodging.getPricePerNight())
                .rating(lodging.getRating())
                .description(lodging.getDescription())
                .availableRooms(lodging.getAvailableRooms())
                .build();
    }
}
