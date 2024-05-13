package com.dropchat.springboot_day1_jpa_crud.repository;

import com.dropchat.springboot_day1_jpa_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
}
