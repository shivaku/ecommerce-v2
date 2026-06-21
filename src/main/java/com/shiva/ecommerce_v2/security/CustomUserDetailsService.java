package com.shiva.ecommerce_v2.security;
import com.shiva.ecommerce_v2.auth.repository.UserRepository;
import com.shiva.ecommerce_v2.auth.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));

        return org.springframework.security.core.userdetails.User
                        .builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
    }
}
