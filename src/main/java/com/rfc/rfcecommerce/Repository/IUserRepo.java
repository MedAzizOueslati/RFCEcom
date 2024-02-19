package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
   Optional<User> findFirstByEmail(String email);
}
