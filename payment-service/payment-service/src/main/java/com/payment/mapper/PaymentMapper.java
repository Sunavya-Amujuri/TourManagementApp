package com.payment.mapper;

import com.payment.dto.PaymentResponse;
import com.payment.entity.Payment;

public class PaymentMapper {
    public static PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .bookingId(payment.getBookingId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .transactionId(payment.getTransactionId())
                .build();
    }

    // We don't need a toEntity() method because the Payment Service will build the entity after retrieving booking details.
}
