package com.rfc.rfcecommerce.service.auth;

import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;

public interface IAuthService {
    public UserDto createUser(SignupRequest signupRequest);
    public Boolean hasUserwithEmail(String email);

    }
