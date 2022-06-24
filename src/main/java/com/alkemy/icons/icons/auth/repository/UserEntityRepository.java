package com.alkemy.icons.icons.auth.repository;

import com.alkemy.icons.icons.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByUsername(String username);
}
