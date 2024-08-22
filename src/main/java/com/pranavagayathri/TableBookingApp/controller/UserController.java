package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping("/users")
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }


    @RequestMapping("/users/{id}")
    public UserDTO getUserId(@PathVariable long id) throws UserNotFoundException {

        return service.getUserById(id);
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user){

        String str=service.addUser(user);
        return ResponseEntity.ok(str);

    }
}
