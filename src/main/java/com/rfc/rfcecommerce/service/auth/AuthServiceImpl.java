package com.rfc.rfcecommerce.service.auth;

import com.rfc.rfcecommerce.entity.*;
import com.rfc.rfcecommerce.repository.IOrderRepo;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.repository.IUserRepo;
import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service

public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserRepo userRepo ;
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private IOrderRepo orderRepo;

    @Value("${admin.default.password}")
    private String defaultAdminPassword;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserDto createUser(SignupRequest signupRequest){


        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CLIENT);
        User createdUser = userRepo.save(user);

        Order order =new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepo.save(order);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return  userDto;
    }

    public Boolean hasUserwithEmail(String email){
        return userRepo.findFirstByEmail(email).isPresent();
    }
    @PostConstruct
    public void createAdminAccount(){


            User adminAccount = userRepo.findByRole(UserRole.ADMIN);
            if (adminAccount == null) {
                User user = new User();
                user.setEmail("admin@test.com");
                user.setName("admin");
                user.setRole(UserRole.ADMIN);
                user.setPassword(passwordEncoder.encode(defaultAdminPassword));
                userRepo.save(user);
            }
        }
    }



