package com.shiva.ecommerce_v2.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {
    
    private String token;

    private String username;

    private String role;

    public UserAuthResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
