package com.Payment_Service.Controller;


import com.Payment_Service.Entity.Payment;
import com.Payment_Service.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@RequestParam Long bookingId,
                               @RequestParam Double amount) {

        return paymentService.makePayment(bookingId, amount);
    }
}
