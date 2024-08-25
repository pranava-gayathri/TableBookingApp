package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() throws UserNotFoundException {
        // Arrange
        List<UserDTO> userDTOList = Arrays.asList(
                new UserDTO(1L, "John Doe", "1234567890"),
                new UserDTO(2L, "Jane Doe", "0987654321")
        );

        when(userService.getAllUsers()).thenReturn(userDTOList);

        // Act
        List<UserDTO> result = userController.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getUserName());
        assertEquals("Jane Doe", result.get(1).getUserName());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserId() throws UserNotFoundException {
        // Arrange
        UserDTO userDTO = new UserDTO(1L, "John Doe", "1234567890");

        when(userService.getUserById(1L)).thenReturn(userDTO);

        // Act
        UserDTO result = userController.getUserId(1L);

        // Assert
        assertEquals("John Doe", result.getUserName());
        assertEquals("1234567890", result.getPhone());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserId_UserNotFound() throws UserNotFoundException {
        // Arrange
        when(userService.getUserById(1L)).thenThrow(new UserNotFoundException("user not found"));

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userController.getUserId(1L);
        });

        assertEquals("user not found", exception.getMessage());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testAddUser() throws UserNotFoundException {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        user.setUserName("John Doe");
        user.setPassword("password");

        when(userService.addUser(user)).thenReturn("register successfully");

        // Act
        ResponseEntity<String> result = userController.addUser(user);

        // Assert
        assertEquals("register successfully", result.getBody());
        verify(userService, times(1)).addUser(user);
    }
}
