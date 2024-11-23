package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.User;
import java.util.List;


public interface MyRepository extends JpaRepository<User,Integer> {
    User   findByEmailAndDomain(String email, String domain);;
}

