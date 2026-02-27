package com.example.Booking_service.ServiceImpl;

import com.example.Booking_service.Client.ServiceCatalogClient;
import com.example.Booking_service.Entity.Booking;
import com.example.Booking_service.Exception.ServiceNotFoundException;
import com.example.Booking_service.Repository.BookingRepository;
import com.example.Booking_service.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ServiceCatalogClient serviceCatalogClient;

    @Override
    public Booking createBooking(String userEmail, Long serviceId) {

        // 🔥 Validate service exists
        try {
            serviceCatalogClient.getServiceById(serviceId);
        } catch (Exception e) {
            throw new ServiceNotFoundException("Service not found with id: " + serviceId);
        }


        Booking booking = new Booking();
        booking.setUserEmail(userEmail);
        booking.setServiceId(serviceId);
        booking.setStatus("PENDING");
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getUserBookings(String userEmail) {
        return bookingRepository.findByUserEmail(userEmail);
    }
}
