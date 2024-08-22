package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.model.Home;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import com.pranavagayathri.TableBookingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Home home){
        String email=home.getEmail();
        String password=home.getPassword();

        User usr=userRepository.findByEmail(email).get();
        if(usr.getEmail().equals(email) && usr.getPassword().equals(password)){
            return ResponseEntity.ok("login successfully");
        }
        return ResponseEntity.ok("incorrect credentials");

    }
}
