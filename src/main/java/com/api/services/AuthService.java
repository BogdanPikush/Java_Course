package com.api.services;

import com.api.dtos.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dtos.SignInDTO;
import com.api.models.User;
import com.api.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // registration
    public void registerUser(SignUpDTO signupRequest) {
        // Check user by email
        if (userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Користувач із таким email вже існує");
        }

        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // create new user
        User newUser = new User(signupRequest.getEmail(), encryptedPassword, signupRequest.getFirstName(), signupRequest.getLastName(), signupRequest.getPhone(), signupRequest.getNumberNewPost());
        userRepository.save(newUser);
    }

    // login
    public void authenticateUser(SignInDTO signinRequest) {
        User user = userRepository.findByEmail(signinRequest.getEmail());

        if (user == null || !passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Невірний email или пароль");
        }

    }
}
