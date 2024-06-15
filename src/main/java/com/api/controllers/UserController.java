package com.api.controllers;

import com.api.dtos.UserDTO;
import com.api.services.JwtService;
import com.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getInfo")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("email") String email) {
        try {
            UserDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserDTO("", "", "", "", 0));
        }
    }

    @PutMapping ("/update")
    public ResponseEntity<String> updateUser(@RequestHeader("email") String email, @RequestBody UserDTO updatedUser) {
        try {
            userService.updateUser(email, updatedUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader("email") String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/getInfo")
//    public ResponseEntity<UserDTO> getUserInfo(HttpServletRequest request) {
//        String token = extractTokenFromCookies(request);
//        System.out.println("Received token: " + token);
//
//        try {
//            UserDTO user = userService.getUserInfo(token);
//            return ResponseEntity.ok(user);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(new UserDTO()); // или любую другую обработку ошибки
//        }
//    }
}
