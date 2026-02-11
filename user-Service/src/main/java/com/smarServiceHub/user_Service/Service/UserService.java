package com.smarServiceHub.user_Service.Service;

import com.smarServiceHub.user_Service.DTO.RegisterRequest;
import com.smarServiceHub.user_Service.Entity.User;

public interface UserService {
    User registerUser(RegisterRequest request);
}
