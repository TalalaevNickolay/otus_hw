package com.otus.mita.simpleservice.repository;

import com.otus.mita.simpleservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
