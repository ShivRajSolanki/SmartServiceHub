package com.Payment_Service.Repository;

import com.Payment_Service.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
