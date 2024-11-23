package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.MyRepository;
import com.user.User;

@Service
public class ImplUserService  implements UserService{
      @Autowired
    MyRepository repo;

    @Override
    public boolean AddUserDetails(User user) {
        try {
            repo.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean IsEmailDomainReg(String email, String domain) {
        return repo.findByEmailAndDomain(email, domain) != null;
    }

}
