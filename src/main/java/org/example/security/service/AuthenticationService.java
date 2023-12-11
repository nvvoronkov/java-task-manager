package org.example.security.service;

import api.exception.UserAlreadyExistException;
import api.exception.UserNotFoundException;
import api.exception.WrongPasswordException;
import api.model.User;
import api.repo.UserRepository;
import api.security.dto.AuthenticationRequest;
import api.security.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Method validate uniqueness of email and creates response with new token
     */
    public AuthenticationResponse register(User request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("ERROR: User with email " + request.getEmail() + " already exist. Try another one");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
        var jwtToken = jwtService.generateToken(request);
        return AuthenticationResponse.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .position(request.getPosition())
                .token(jwtToken)
                .build();
    }

    /**
     * Method authenticates incoming request based on email and password and generates Authentication response with token
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new WrongPasswordException("ERROR: Wrong password");
        }
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("ERROR: User with email " + request.getEmail() + " not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .position(user.getPosition())
                .token(jwtToken)
                .build();
    }
}
