package com.watuka.ServiceBookingSystem.Services.Authentication;

import com.watuka.ServiceBookingSystem.Dto.SignupRequestDTO;
import com.watuka.ServiceBookingSystem.Dto.UserDto;

public interface AuthService {

    UserDto signupClient(SignupRequestDTO signupRequestDTO);

    Boolean presentByEmail(String email);

    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}
