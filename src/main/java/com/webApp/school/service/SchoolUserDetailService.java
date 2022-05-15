package com.webApp.school.service;

import com.webApp.school.model.SchoolUserDetails;
import com.webApp.school.model.User;
import com.webApp.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(userEmail);
        if (optional.isPresent()) {
            return new SchoolUserDetails(optional.get());
        } else {
            throw new UsernameNotFoundException("User by email: " + userEmail + " not found.");
        }
    }
}
