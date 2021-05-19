package com.francis.weekeighttasktodoapplication.repository;

import com.francis.weekeighttasktodoapplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);
    Users getUsersById(Long id);
    Users findUserByEmailAndPassword(String email, String password);
}
