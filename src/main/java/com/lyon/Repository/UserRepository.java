package com.lyon.Repository;

import com.lyon.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByIdAndPasswordAndIdentity(long id, String password, short identity);
}