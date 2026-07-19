package com.payment.service;

import com.payment.client.BookingClient;
import com.payment.dto.BookingResponse;
import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import com.payment.entity.Payment;
import com.payment.mapper.PaymentMapper;
import com.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingClient bookingClient;

    public PaymentResponse createPayment(PaymentRequest request) {

        // Get booking details
        BookingResponse booking = bookingClient.getBookingById(request.getBookingId());

        // Create payment
        Payment payment = Payment.builder()
                .bookingId(booking.getBookingId())
                .amount(booking.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        // Confirm booking
        bookingClient.confirmBooking(request.getBookingId());

        return PaymentMapper.toResponse(savedPayment);
    }

    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return PaymentMapper.toResponse(payment);
    }

    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }
}
