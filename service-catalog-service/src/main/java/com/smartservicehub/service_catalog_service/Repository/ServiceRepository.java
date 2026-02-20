package com.smartservicehub.service_catalog_service.Repository;

import com.smartservicehub.service_catalog_service.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {


}
