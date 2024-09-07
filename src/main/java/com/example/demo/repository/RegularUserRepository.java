package com.example.demo.repository;

import com.example.demo.model.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {
    // You can define custom queries here if needed
}
