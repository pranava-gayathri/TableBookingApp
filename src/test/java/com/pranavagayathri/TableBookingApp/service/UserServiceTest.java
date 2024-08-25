package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.helpermethods.EntityToDTO;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private EntityToDTO entityToDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() throws UserNotFoundException {
        // Mocking data
        User user1 = new User(1L, "John Doe", "john@gmail.com", "password123", "+1234567890", "Address1", new ArrayList<>());
        User user2 = new User(2L, "Jane Doe", "jane@gmail.com", "password456", "+0987654321", "Address2", new ArrayList<>());

        // Mock the UserDTOs
        UserDTO userDTO1 = new UserDTO(1L, "John Doe",  "+1234567890");
        UserDTO userDTO2 = new UserDTO(2L, "Jane Doe",  "+0987654321");

        // Mock repository and entityToDTO conversion
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(entityToDTO.userToUserDTO(user1)).thenReturn(userDTO1);
        when(entityToDTO.userToUserDTO(user2)).thenReturn(userDTO2);

        // Test
        var userDTOList = userService.getAllUsers();

        // Verifications
        assertEquals(2, userDTOList.size());
        assertEquals("John Doe", userDTOList.get(0).getUserName());
        assertEquals("Jane Doe", userDTOList.get(1).getUserName());
        verify(userRepository, times(1)).findAll();
        verify(entityToDTO, times(1)).userToUserDTO(user1);
        verify(entityToDTO, times(1)).userToUserDTO(user2);
    }


    @Test
    void testGetUserByIdSuccess() throws UserNotFoundException {
        // Mock data
        User user = new User(1L, "John Doe", "john@gmail.com", "password123", "+1234567890", "Address1", null);
        UserDTO userDTO = new UserDTO(1L, "John Doe", "+1234567890");

        // Mock the repository and entity conversion
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(entityToDTO.userToUserDTO(user)).thenReturn(userDTO);

        // Test the method
        UserDTO result = userService.getUserById(1L);

        // Verifications
        assertNotNull(result);
        assertEquals(userDTO, result); // Compare with the expected UserDTO
        verify(userRepository, times(1)).findById(1L);
        verify(entityToDTO, times(1)).userToUserDTO(user);
    }

    @Test
    void testGetUserByIdThrowsUserNotFoundException() {
        // Mocking data
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Test & Verifications
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test


    void testAddUser() {
        // Mocking data
        User user = new User(1L, "John Doe", "john@gmail.com", "password123", "+1234567890", "Address1", null);

        // Use a real BCryptPasswordEncoder for encoding
        BCryptPasswordEncoder realBCrypt = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encryptedPassword = realBCrypt.encode(rawPassword); // Encrypt the password using the real encoder

        // Mock the repository behavior but don't mock BCryptPasswordEncoder
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Manually set the password to the encrypted value for testing purposes
        user.setPassword(encryptedPassword);

        // Test the method
        String result = userService.addUser(user);

        // Verifications
        assertEquals("registration Successful", result);

        // Use the real BCryptPasswordEncoder to verify the password matches
        assertTrue(realBCrypt.matches(rawPassword, encryptedPassword));

        // Ensure the save method was called exactly once
        verify(userRepository, times(1)).save(any(User.class));
    }




    @Test
    void testAddUserFailure() throws UserNotFoundException {
        // Mocking data
        User user = new User(1L, "John Doe", "john@gmail.com", "password123", "+1234567890", "Address1", null);
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Database error"));

        // Test
        String result = userService.addUser(user);

        // Verifications
        assertEquals("Database error", result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
