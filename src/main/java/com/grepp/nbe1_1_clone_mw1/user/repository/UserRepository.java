package com.grepp.nbe1_1_clone_mw1.user.repository;

import com.grepp.nbe1_1_clone_mw1.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, byte[]> {
}
