package com.pranavagayathri.TableBookingApp.config;


import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceClass implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("User Not found!");
        return new UserPrinciple(user);
    }

}

