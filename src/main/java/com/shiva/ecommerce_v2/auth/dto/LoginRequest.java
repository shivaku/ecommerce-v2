package com.shiva.ecommerce_v2.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    
    private String username;

    private String password;
}
