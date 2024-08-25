package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<UserDTO> getAllUsers() throws UserNotFoundException;

    UserDTO getUserById(Long id) throws UserNotFoundException;

    String addUser(User user);
}
