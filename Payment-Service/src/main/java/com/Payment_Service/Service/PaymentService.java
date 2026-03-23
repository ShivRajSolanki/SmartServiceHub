package com.Payment_Service.Service;


import com.Payment_Service.Client.BookingClient;
import com.Payment_Service.Entity.Payment;
import com.Payment_Service.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingClient bookingClient;

    public Payment makePayment(Long bookingId, Double amount) {

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setStatus("SUCCESS");
        payment.setPaymentTime(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);


        bookingClient.confirmBooking(bookingId, "ADMIN");

        return saved;
    }
}
