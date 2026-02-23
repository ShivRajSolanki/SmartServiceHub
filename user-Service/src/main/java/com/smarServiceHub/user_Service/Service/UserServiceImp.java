package com.smarServiceHub.user_Service.Service;

import com.smarServiceHub.user_Service.DTO.LoginRequest;
import com.smarServiceHub.user_Service.DTO.RegisterRequest;
import com.smarServiceHub.user_Service.Entity.User;
import com.smarServiceHub.user_Service.Repository.UserRepository;
import com.smarServiceHub.user_Service.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImp  implements  UserService{

    @Autowired
   private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }
    @Override
    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

}
