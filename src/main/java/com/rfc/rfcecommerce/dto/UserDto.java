package com.rfc.rfcecommerce.dto;

import com.rfc.rfcecommerce.Entity.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
