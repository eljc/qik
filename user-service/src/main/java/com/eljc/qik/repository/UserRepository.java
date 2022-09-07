package com.eljc.qik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eljc.qik.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);
}
