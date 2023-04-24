package com.example.userservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * from auth.users WHERE id=?1", nativeQuery = true)
    User getById(int id);
}
