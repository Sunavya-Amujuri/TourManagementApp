package com.tour.service;

import com.tour.client.LocationClient;
import com.tour.client.LodgingClient;
import com.tour.client.TransportClient;
import com.tour.dto.*;
import com.tour.entity.Tour;
import com.tour.exception.ResourceNotFoundException;
import com.tour.mapper.TourMapper;
import com.tour.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    private final LocationServiceHelper locationServiceHelper;

    private final LodgingServiceHelper lodgingServiceHelper;

    private final TransportServiceHelper transportServiceHelper;


    @Override
    public TourResponse createTour(TourRequest request) {
//        Tour tour = TourMapper.toEntity(request);
//
//        Tour savedTour = tourRepository.save(tour);
//
//        return TourMapper.toResponse(savedTour);
        Tour tour = TourMapper.toEntity(request);
        Tour savedTour = tourRepository.save(tour);

        TourResponse response = TourMapper.toResponse(savedTour);
        if (savedTour.getLocationId() != null) {
            response.setLocation(locationServiceHelper.getLocation(savedTour.getLocationId()));
        }

        if (savedTour.getLodgingId() != null) {
            response.setLodging(
                    lodgingServiceHelper.getLodging(savedTour.getLodgingId())
            );
        }

        if (savedTour.getTransportId() != null) {
            response.setTransport(
                    transportServiceHelper.getTransport(savedTour.getTransportId())
            );
        }

        return response;
    }

    @Override
    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRepository.findAll();

        return tours.stream().map(tour -> {
            TourResponse response = TourMapper.toResponse(tour);
            if (tour.getLocationId() != null) {
                response.setLocation(locationServiceHelper.getLocation(tour.getLocationId()));
            }

            if (tour.getLodgingId() != null) {
                response.setLodging(
                        lodgingServiceHelper.getLodging(tour.getLodgingId())
                );
            }

            if (tour.getTransportId() != null) {
                response.setTransport(
                        transportServiceHelper.getTransport(tour.getTransportId())
                );
            }

            return response;
        }).toList();
    }

    @Override
    public TourResponse getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found with id: " + id));

        TourResponse response = TourMapper.toResponse(tour);

        if (tour.getLocationId() != null) {
            LocationResponse location = locationServiceHelper.getLocation(tour.getLocationId());
            response.setLocation(location);
        }

        if (tour.getLodgingId() != null) {
            response.setLodging(lodgingServiceHelper.getLodging(tour.getLodgingId()));
        }

        if (tour.getTransportId() != null) {
            response.setTransport(transportServiceHelper.getTransport(tour.getTransportId()));
        }

        return response;
    }

    @Override
    public TourResponse updateTour(Long id, TourRequest request) {
        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found with id: " + id));

        existingTour.setTourName(request.getTourName());
        existingTour.setTourDescription(request.getTourDescription());
        existingTour.setPrice(request.getPrice());
        existingTour.setTourGuide(request.getTourGuide());
        existingTour.setStartDate(request.getStartDate());
        existingTour.setEndDate(request.getEndDate());
        existingTour.setMeals(request.getMeals());
        existingTour.setActivities(request.getActivities());
        existingTour.setAvailableSeats(request.getAvailableSeats());
        existingTour.setTourImages(request.getTourImages());
        existingTour.setLocationId(request.getLocationId());
        existingTour.setLodgingId(request.getLodgingId());
        existingTour.setTransportId(request.getTransportId());

        Tour updatedTour = tourRepository.save(existingTour);

        TourResponse response = TourMapper.toResponse(updatedTour);
        if (updatedTour.getLocationId() != null) {
            response.setLocation(locationServiceHelper.getLocation(updatedTour.getLocationId()));
        }

        if (updatedTour.getLodgingId() != null) {
            response.setLodging(lodgingServiceHelper.getLodging(updatedTour.getLodgingId()));;
        }

        if (updatedTour.getTransportId() != null) {
            response.setTransport(transportServiceHelper.getTransport(updatedTour.getTransportId()));
        }

        return response;
    }

    @Override
    public void deleteTour(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found with id: " + id));

        tourRepository.delete(tour);
    }

    public void reserveSeats(Long tourId, Integer seats) {

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        if (tour.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats available");
        }

        tour.setAvailableSeats(tour.getAvailableSeats() - seats);

        tourRepository.save(tour);
    }
}
