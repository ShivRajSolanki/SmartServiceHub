package com.smarServiceHub.user_Service.Confi;

import com.smarServiceHub.user_Service.Entity.User;
import com.smarServiceHub.user_Service.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class AdminInitializer {



        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @PostConstruct
        public void createAdmin() {
            System.out.println("AdminInitializer running...");

            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");

                userRepository.save(admin);
                System.out.println("Admin user created!");
            }
        }
    }

