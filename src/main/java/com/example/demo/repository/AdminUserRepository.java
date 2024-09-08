package com.example.demo.repository;

import com.example.demo.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    // Find an admin user by username
    Optional<AdminUser> findByUsername(String username);

    // Find an admin user by email
    Optional<AdminUser> findByEmail(String email);
}
