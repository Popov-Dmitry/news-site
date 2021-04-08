package com.github.PopovDmitry.nstu.webcw.repository;

import com.github.PopovDmitry.nstu.webcw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
}
