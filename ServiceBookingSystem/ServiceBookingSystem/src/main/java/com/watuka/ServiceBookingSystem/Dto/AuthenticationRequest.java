package com.watuka.ServiceBookingSystem.Dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;

    private String password;
}
