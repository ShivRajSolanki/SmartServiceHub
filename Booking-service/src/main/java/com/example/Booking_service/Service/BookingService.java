package com.example.Booking_service.Service;

import com.example.Booking_service.Entity.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {
    Booking createBooking(String userEmail, Long serviceId);
    List<Booking> getUserBookings(String userEmail);
}
