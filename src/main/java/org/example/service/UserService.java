package org.example.service;

import org.example.model.User;
import org.example.model.dto.UserResponse;

public interface UserService {

    User getUserById(int id);

    UserResponse updateUser(User user, int id);

    UserResponse getUserResponse(int id);
}
