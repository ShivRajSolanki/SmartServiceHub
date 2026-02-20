package com.smartservicehub.service_catalog_service.Service;


import com.smartservicehub.service_catalog_service.Entity.ServiceEntity;
import com.smartservicehub.service_catalog_service.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCatalogService {

    @Autowired
    private ServiceRepository serviceRepository;

    public ServiceEntity addService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }
}
