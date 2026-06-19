package com.shiva.ecommerce_v2.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shiva.ecommerce_v2.JwtService.JwtService;
import com.shiva.ecommerce_v2.auth.dto.LoginRequest;
import com.shiva.ecommerce_v2.auth.dto.LoginResponse;
import com.shiva.ecommerce_v2.auth.dto.UserAuthResponse;
import com.shiva.ecommerce_v2.auth.dto.UserRequest;
import com.shiva.ecommerce_v2.auth.entity.User;
import com.shiva.ecommerce_v2.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserAuthResponse register(UserRequest userRequest) {
          User user = new User();
          
          user.setUsername(userRequest.getUsername());
          user.setPassword(
            passwordEncoder.encode(
                userRequest.getPassword()
            )
        );
          user.setRole("USER");
          User saveUser = userRepository.save(user);

        return mapToUserAuthResponse(saveUser);
    }

    private UserAuthResponse mapToUserAuthResponse(User user) {
        return new UserAuthResponse(
            user.getUsername(),
            user.getRole()
        );
    }
    
    public LoginResponse login(LoginRequest loginRequest) {
        User user =  userRepository
                    .findByUsername(loginRequest.getUsername())
                    .orElseThrow(()->new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                        loginRequest.getPassword(), 
                        user.getPassword()
                    );
        if(!matches) {
            throw new RuntimeException("Invalid password");
        }

        String token =  jwtService.generateToken(user.getUsername());

        return new LoginResponse(
            token, 
            user.getUsername(),
            user.getRole()
            );
    } 
}
