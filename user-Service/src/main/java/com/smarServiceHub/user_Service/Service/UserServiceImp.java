package com.smarServiceHub.user_Service.Service;

import com.smarServiceHub.user_Service.DTO.RegisterRequest;
import com.smarServiceHub.user_Service.Entity.User;
import com.smarServiceHub.user_Service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp  implements  UserService{

    @Autowired
   private UserRepository userRepository;

    public User registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        return userRepository.save(user);
    }

}
