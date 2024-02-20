package com.rfc.rfcecommerce.Service.auth;

import com.rfc.rfcecommerce.Entity.User;
import com.rfc.rfcecommerce.Entity.UserRole;
import com.rfc.rfcecommerce.Repository.IUserRepo;
import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserRepo userRepo ;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CLIENT);
        User createdUser = userRepo.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return  userDto;
    }

    public Boolean hasUserwithEmail(String email){
        return userRepo.findFirstByEmail(email).isPresent();
    }

}
