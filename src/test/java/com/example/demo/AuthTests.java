package com.example.demo;

import com.api.controllers.AuthController;
import com.api.dtos.SignUpDTO;
import com.api.dtos.SignInDTO;
import com.api.services.AuthService;
import com.api.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthTests {
    @Mock
    private AuthService authService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    private SignUpDTO signUpDTO;
    private SignInDTO signInDTO;

    @BeforeEach
    void setUp() {
        signUpDTO = new SignUpDTO();
        signUpDTO.setEmail("test@example.com");
        signUpDTO.setPassword("password");
        signUpDTO.setFirstName("First");
        signUpDTO.setLastName("Last");
        signUpDTO.setPhone("1234567890");
        signUpDTO.setNumberNewPost(123);

        signInDTO = new SignInDTO();
        signInDTO.setEmail("test@example.com");
        signInDTO.setPassword("password");
    }

    @Test
    void registration_ReturnsSuccessMessage() {
        doNothing().when(authService).registerUser(any(SignUpDTO.class));

        ResponseEntity<String> response = authController.registration(signUpDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User created successfully", response.getBody());
        verify(authService, times(1)).registerUser(any(SignUpDTO.class));
    }

    @Test
    void registration_InvalidData_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Invalid data")).when(authService).registerUser(any(SignUpDTO.class));

        ResponseEntity<String> response = authController.registration(signUpDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid data", response.getBody());
        verify(authService, times(1)).registerUser(any(SignUpDTO.class));
    }

    @Test
    void login_ReturnsTokenAndEmail() {
        doNothing().when(authService).authenticateUser(any(SignInDTO.class));
        when(jwtService.generateToken(anyString())).thenReturn("jwt-token");

        ResponseEntity<Map<String, String>> response = authController.login(signInDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("jwt-token", response.getBody().get("token"));
        assertEquals("test@example.com", response.getBody().get("email"));
        verify(authService, times(1)).authenticateUser(any(SignInDTO.class));
        verify(jwtService, times(1)).generateToken(anyString());
    }

    @Test
    void login_InvalidCredentials_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Invalid email or password")).when(authService).authenticateUser(any(SignInDTO.class));

        ResponseEntity<Map<String, String>> response = authController.login(signInDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid email or password", response.getBody().get("error"));
        verify(authService, times(1)).authenticateUser(any(SignInDTO.class));
        verify(jwtService, never()).generateToken(anyString());
    }
}
