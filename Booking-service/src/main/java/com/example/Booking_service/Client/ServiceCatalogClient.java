package com.example.Booking_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-catalog-service")
public interface ServiceCatalogClient {

    @GetMapping("/services/{id}")
    Object getServiceById(@PathVariable Long id);
}
