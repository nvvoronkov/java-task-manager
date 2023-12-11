package org.example.service;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.model.dto.UserResponse;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserResponse EntityToDto(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPosition(user.getPosition());
        return response;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("ERROR: User with id " + id + " not found"));
    }

    @Override
    public UserResponse getUserResponse(int id) {
        return EntityToDto(getUserById(id));
    }

    @Override
    public UserResponse updateUser(User user, int id) {
        User prev = getUserById(id);
        if (user.getFirstName() != null) prev.setFirstName(user.getFirstName());
        if (user.getLastName() != null) prev.setLastName(user.getLastName());
        if (user.getPosition() != null) prev.setPosition(user.getPosition());
        prev.setId(id);
        return EntityToDto(userRepository.save(prev));
    }
}
