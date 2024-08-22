package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public List<UserDTO> getAllUsers() {
        List<User> userList=repo.findAll();
        List<UserDTO> userDTOS=new ArrayList<>();
        for(User u:userList){
            UserDTO udto=new UserDTO();
            udto.setUserId(u.getUserId());
            udto.setUserName(u.getUserName());
            udto.setPhone(u.getPhone());

            userDTOS.add(udto);

        }

        return userDTOS;
    }
    public UserDTO getUserById(Long id) throws UserNotFoundException {

        Optional<User> usr=repo.findById(id);
        if(!usr.isPresent()){
            throw new UserNotFoundException("user not found");
        }
        else{
            User user=repo.findById(id).get();
            UserDTO userDTO=new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setPhone(user.getPhone());
        userDTO.setUserId(user.getUserId());
        return userDTO;}

    }

    public String addUser(User user) {
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            repo.save(user);
            return "register successfully";
        } catch (Exception e) {

            return e.getMessage();
        }

    }
}
