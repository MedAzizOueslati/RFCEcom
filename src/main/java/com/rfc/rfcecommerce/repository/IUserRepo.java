package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.User;
import com.rfc.rfcecommerce.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
   Optional<User> findFirstByEmail(String email);
   User findByRole(UserRole userRole);
}
