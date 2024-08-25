package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.helpermethods.EntityToDTO;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository repo;

    @Autowired
    private EntityToDTO entityToDTO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDTO> getAllUsers() throws UserNotFoundException {

        List<User> userList=repo.findAll();
        if (!userList.isEmpty()){
        List<UserDTO> userDTOS=new ArrayList<>();
        for(User u:userList){
            UserDTO udto=entityToDTO.userToUserDTO(u);
            userDTOS.add(udto);
        }
        return userDTOS;
        }
        else{
            throw new UserNotFoundException("error in getting all errors");
        }
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User usr=repo.findById(id).orElse(null);
        if(usr!=null){

            return entityToDTO.userToUserDTO(usr);
        }
        else{
            throw new UserNotFoundException("user not found");
        }
    }




    @Override
    public String addUser(User user) {
        try {
            if(bCryptPasswordEncoder.encode(user.getPassword())!=null) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                repo.save(user);
                return "registration Successful";
            }
            else{
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            // Handle the exception here, such as logging or returning a custom message
            return e.getMessage();
        }
    }

}
