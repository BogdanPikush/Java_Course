package com.api.services;

import com.api.dtos.UserDTO;
import com.api.models.User;
import com.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getNumberNewPost()))
                .collect(Collectors.toList());
    }

//    public UserDTO getUserInfo(String token) {
//        String email = jwtService.validateToken(token);
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new IllegalArgumentException("User not found");
//        }
//        return new UserDTO(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getNumberNewPost());
//    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserDTO(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getNumberNewPost());
    }

    public void updateUser(String email, UserDTO updatedUser) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setNumberNewPost(updatedUser.getNumberNewPost());
        userRepository.save(existingUser);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
    }

}
