package com.services;

import com.user.User;

public interface UserService {
    public boolean AddUserDetails(User user);
    public boolean IsEmailDomainReg(String email, String domain);
}
