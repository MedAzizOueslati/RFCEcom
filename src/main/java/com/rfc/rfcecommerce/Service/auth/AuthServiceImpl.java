package com.rfc.rfcecommerce.Service.auth;

import com.rfc.rfcecommerce.Entity.*;
import com.rfc.rfcecommerce.Repository.IOrderRepo;
import com.rfc.rfcecommerce.Repository.IProductRepo;
import com.rfc.rfcecommerce.Repository.IUserRepo;
import com.rfc.rfcecommerce.dto.SignupRequest;
import com.rfc.rfcecommerce.dto.UserDto;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service

public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserRepo userRepo ;
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private IOrderRepo orderRepo;


    public UserDto createUser(SignupRequest signupRequest){
        System.out.println("signupRequest");
        System.out.println(signupRequest);

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CLIENT);
        User createdUser = userRepo.save(user);
        System.out.println(user.toString());

        Order order =new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Pending);
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
        if (null == adminAccount ){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("aziz");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("Admin@123"));
            userRepo.save(user);

        }
    }


}
