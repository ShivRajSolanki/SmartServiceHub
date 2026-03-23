package com.Payment_Service.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingClient {


    @PutMapping("/bookings/{id}/confirm")
    void confirmBooking(@PathVariable Long id,
                        @RequestHeader("X-User-Role") String role);
}
