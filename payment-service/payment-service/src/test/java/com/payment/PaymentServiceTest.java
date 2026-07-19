package com.payment;

import com.payment.client.BookingClient;
import com.payment.dto.BookingResponse;
import com.payment.dto.PaymentRequest;
import com.payment.dto.PaymentResponse;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BookingClient bookingClient;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testCreatePaymentSuccessfully() {

        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setBookingId(1L);
        request.setPaymentMethod("UPI");

        BookingResponse booking = new BookingResponse();
        booking.setBookingId(1L);
        booking.setUserId(1L);
        booking.setTourId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setNumberOfPersons(2);
        booking.setTotalAmount(30000.0);
        booking.setBookingStatus("PENDING");

        Payment savedPayment = Payment.builder()
                .paymentId(1L)
                .bookingId(1L)
                .amount(30000.0)
                .paymentMethod("UPI")
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .transactionId("TXN123456")
                .build();

        when(bookingClient.getBookingById(1L))
                .thenReturn(booking);

        when(paymentRepository.save(any(Payment.class)))
                .thenReturn(savedPayment);

        when(bookingClient.confirmBooking(1L))
                .thenReturn("Booking Confirmed");

        // Act
        PaymentResponse response = paymentService.createPayment(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getPaymentId());
        assertEquals(1L, response.getBookingId());
        assertEquals(30000.0, response.getAmount());
        assertEquals("UPI", response.getPaymentMethod());
        assertEquals("SUCCESS", response.getPaymentStatus());

        verify(bookingClient).getBookingById(1L);
        verify(paymentRepository).save(any(Payment.class));
        verify(bookingClient).confirmBooking(1L);
    }

    @Test
    void testGetPaymentById() {

        Payment payment = Payment.builder()
                .paymentId(1L)
                .bookingId(1L)
                .amount(30000.0)
                .paymentMethod("UPI")
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .transactionId("TXN123456")
                .build();

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.of(payment));

        PaymentResponse response = paymentService.getPaymentById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getPaymentId());
        assertEquals("SUCCESS", response.getPaymentStatus());

        verify(paymentRepository).findById(1L);
    }

    @Test
    void testGetPaymentById_NotFound() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> paymentService.getPaymentById(1L)
        );

        assertEquals("Payment not found", exception.getMessage());

        verify(paymentRepository).findById(1L);
    }

    @Test
    public void testGetAllPayments(){
        Payment payment1 = Payment.builder()
                .paymentId(1L)
                .bookingId(1L)
                .amount(30000.0)
                .paymentMethod("UPI")
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .transactionId("TXN123")
                .build();

        Payment payment2 = Payment.builder()
                .paymentId(2L)
                .bookingId(2L)
                .amount(15000.0)
                .paymentMethod("CARD")
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .transactionId("TXN456")
                .build();

        when(paymentRepository.findAll())
                .thenReturn(List.of(payment1, payment2));

        List<PaymentResponse> responses = paymentService.getAllPayments();

        assertNotNull(responses);
        assertEquals(2, responses.size());

        verify(paymentRepository).findAll();
    }
}

