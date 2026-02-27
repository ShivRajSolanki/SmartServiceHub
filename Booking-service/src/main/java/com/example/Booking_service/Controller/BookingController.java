package com.example.Booking_service.Controller;


import com.example.Booking_service.Entity.Booking;
import com.example.Booking_service.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;



    @PostMapping
    public Booking createBooking(
            @RequestHeader("X-User-Email") String userEmail,
            @RequestParam Long serviceId) {

        return bookingService.createBooking(userEmail, serviceId);
    }

    @GetMapping
    public List<Booking> getBookings(@RequestParam String userEmail) {
        return bookingService.getUserBookings(userEmail);
    }
}
