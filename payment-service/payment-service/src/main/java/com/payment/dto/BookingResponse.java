package com.payment.dto;

import java.time.LocalDate;

public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private Long tourId;
    private LocalDate bookingDate;
    private Integer numberOfPersons;
    private Double totalAmount;
    private String bookingStatus;
    private String userEmail;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BookingResponse(Integer numberOfPersons, Long bookingId, Long userId, Long tourId, LocalDate bookingDate, Double totalAmount, String bookingStatus, String userEmail) {
        this.numberOfPersons = numberOfPersons;
        this.bookingId = bookingId;
        this.userId = userId;
        this.tourId = tourId;
        this.userEmail = userEmail;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
    }

    public BookingResponse() {

    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", tourId=" + tourId +
                ", bookingDate=" + bookingDate +
                ", userEmail='" + userEmail + '\'' +
                ", numberOfPersons=" + numberOfPersons +
                ", totalAmount=" + totalAmount +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
