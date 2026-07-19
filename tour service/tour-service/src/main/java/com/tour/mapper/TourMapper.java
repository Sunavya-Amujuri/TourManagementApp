package com.tour.mapper;

import com.tour.dto.TourRequest;
import com.tour.dto.TourResponse;
import com.tour.entity.Tour;

public class TourMapper {
    private TourMapper() {
    }

    public static Tour toEntity(TourRequest request) {
        return Tour.builder()
                .tourName(request.getTourName())
                .tourDescription(request.getTourDescription())
                .tourGuide(request.getTourGuide())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .meals(request.getMeals())
                .activities(request.getActivities())
                .price(request.getPrice())
                .availableSeats(request.getAvailableSeats())
                .tourImages(request.getTourImages())
                .locationId(request.getLocationId())
                .lodgingId(request.getLodgingId())
                .transportId(request.getTransportId())
                .build();
    }

    public static TourResponse toResponse(Tour tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .tourDescription(tour.getTourDescription())
                .tourGuide(tour.getTourGuide())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .meals(tour.getMeals())
                .activities(tour.getActivities())
                .price(tour.getPrice())
                .availableSeats(tour.getAvailableSeats())
                .tourImages(tour.getTourImages())
                .locationId(tour.getLocationId())
                .lodgingId(tour.getLodgingId())
                .transportId(tour.getTransportId())
                .build();
    }
}
