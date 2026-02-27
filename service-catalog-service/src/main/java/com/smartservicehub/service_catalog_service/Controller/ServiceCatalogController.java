package com.smartservicehub.service_catalog_service.Controller;


import com.smartservicehub.service_catalog_service.Entity.ServiceEntity;
import com.smartservicehub.service_catalog_service.Repository.ServiceRepository;
import com.smartservicehub.service_catalog_service.Service.ServiceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceCatalogController {


    @Autowired
    private ServiceCatalogService service;

    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping
    public ServiceEntity addService(@RequestBody ServiceEntity entity) {
        return service.addService(entity);
    }

    @GetMapping
    public List<ServiceEntity> getAll() {
        return service.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {

        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        return ResponseEntity.ok(service);
    }
}
