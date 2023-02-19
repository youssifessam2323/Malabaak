package io.joework.Malabaak.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {
    private final UserDetailsService userDetailsService;

    public CustomUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetails getUserByEmail(String userEmail) {
        return userDetailsService.loadUserByUsername(userEmail);
    }
}
