package com.example.demo;

import com.api.controllers.UserController;
import com.api.dtos.UserDTO;
import com.api.services.JwtService;
import com.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThingTests {
    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO("test@example.com", "John", "Doe", "1234567890", 1);
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        List<UserDTO> userList = Arrays.asList(userDTO, userDTO);
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserInfo_ValidEmail_ReturnsUser() {
        when(userService.getUserByEmail(anyString())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserInfo("test@example.com");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUserByEmail("test@example.com");
    }

    @Test
    void updateUser_ValidEmail_ReturnsSuccessMessage() {
        doNothing().when(userService).updateUser(anyString(), any(UserDTO.class));

        ResponseEntity<String> response = userController.updateUser("test@example.com", userDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User updated successfully", response.getBody());
        verify(userService, times(1)).updateUser("test@example.com", userDTO);
    }

    @Test
    void updateUser_InvalidEmail_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("User not found")).when(userService).updateUser(anyString(), any(UserDTO.class));

        ResponseEntity<String> response = userController.updateUser("invalid@example.com", userDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("User not found", response.getBody());
        verify(userService, times(1)).updateUser("invalid@example.com", userDTO);
    }

    @Test
    void deleteUser_ValidEmail_ReturnsSuccessMessage() {
        doNothing().when(userService).deleteUser(anyString());

        ResponseEntity<String> response = userController.deleteUser("test@example.com");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User deleted successfully", response.getBody());
        verify(userService, times(1)).deleteUser("test@example.com");
    }

    @Test
    void deleteUser_InvalidEmail_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("User not found")).when(userService).deleteUser(anyString());

        ResponseEntity<String> response = userController.deleteUser("invalid@example.com");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("User not found", response.getBody());
        verify(userService, times(1)).deleteUser("invalid@example.com");
    }
}
