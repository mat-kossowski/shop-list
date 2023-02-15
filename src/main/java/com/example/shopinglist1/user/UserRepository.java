package com.example.shopinglist1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findUserByUserId(Long userId);

   Optional <User> findUserByUserName(String username);



  Optional<User> findUserByEmail(String email);


    Boolean existsByEmail(String email);
}
