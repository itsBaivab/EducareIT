package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.User;

@Repository
public interface MyRepository extends JpaRepository<User,Integer> {
    User   findByEmailAndDomain(String email, String domain);;
}

