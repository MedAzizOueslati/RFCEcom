package com.rfc.rfcecommerce.Service.auth;

import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;
import org.springframework.stereotype.Repository;

public interface IAuthService {
    public UserDto createUser(SignupRequest signupRequest);
    public Boolean hasUserwithEmail(String email);

    }
