package com.tour.service;

import com.tour.client.LocationClient;
import com.tour.client.LodgingClient;
import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;
import com.tour.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TourService {

    TourResponse createTour(TourRequest request);

    List<TourResponse> getAllTours();

    TourResponse getTourById(Long id);

    TourResponse updateTour(Long id, TourRequest request);

    void deleteTour(Long id);

    public void reserveSeats(Long tourId, Integer seats);

}
