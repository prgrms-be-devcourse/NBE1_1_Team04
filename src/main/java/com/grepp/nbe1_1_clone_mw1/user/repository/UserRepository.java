package com.grepp.nbe1_1_clone_mw1.user.repository;

import com.grepp.nbe1_1_clone_mw1.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, byte[]> {
    Optional<User> findByEmail(String email);
}
