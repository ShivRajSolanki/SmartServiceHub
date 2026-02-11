package com.smarServiceHub.user_Service.Controller;

import com.smarServiceHub.user_Service.DTO.RegisterRequest;
import com.smarServiceHub.user_Service.Entity.User;
import com.smarServiceHub.user_Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/health")
    public String health() {
        return "User Service is running!";
    }
    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody RegisterRequest request){
        User savedUser = userService.registerUser(request);
        return ResponseEntity.ok(savedUser);
    }


}
