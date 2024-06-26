package com.beas.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beas.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
    List<User> findByOnline(boolean online);

}
