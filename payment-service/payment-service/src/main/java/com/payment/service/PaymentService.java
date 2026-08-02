package com.payment.service;

import com.payment.client.BookingClient;
import com.payment.client.NotificationClient;
import com.payment.dto.BookingResponse;
import com.payment.dto.NotificationRequest;
import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import com.payment.entity.Payment;
import com.payment.exception.ResourceNotFoundException;
import com.payment.mapper.PaymentMapper;
import com.payment.repository.PaymentRepository;
import feign.FeignException;
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
    private final NotificationClient notificationClient;


    public PaymentResponse createPayment(PaymentRequest request) {

        BookingResponse booking;

        // Get booking details with exception handling
        try {

            booking = bookingClient.getBookingById(request.getBookingId());

            if (booking == null) {
                throw new ResourceNotFoundException(
                        "Booking not found with id: " + request.getBookingId()
                );
            }

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Booking not found with id: " + request.getBookingId()
            );

        } catch (FeignException ex) {

            throw new RuntimeException(
                    "Booking service unavailable"
            );
        }
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

        // Send notification
        NotificationRequest notification = new NotificationRequest();
        notification.setBookingId(request.getBookingId());
        notification.setRecipient(booking.getUserEmail()); // Replace later with actual user email
        notification.setSubject("Payment Successful");
        notification.setMessage(
                "Your payment for booking #" + request.getBookingId()
                        + " was successful. Your booking has been confirmed."
        );

        System.out.println("Recipient = " + notification.getRecipient());
        System.out.println("Subject = " + notification.getSubject());
        System.out.println("Message = " + notification.getMessage());

        notificationClient.sendNotification(notification);

        // Return payment response
        return PaymentMapper.toResponse(savedPayment);
    }


    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );

        return PaymentMapper.toResponse(payment);
    }


    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }
}
