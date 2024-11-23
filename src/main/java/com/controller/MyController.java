package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.services.UserService;
import com.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MyController {
@Autowired
UserService Userserv;

@GetMapping("/user_apply")
    public String getMethodName(Model model) {
        model.addAttribute("user", new User());
        return "from";
}
 @PostMapping("/addinfo")
        public String SaveUserReg(@ModelAttribute("user") User user, Model model) {
          if (Userserv.IsEmailDomainReg(user.getEmail(), user.getDomain()) ){
            model.addAttribute("FailMsg", "Email is already Applied with same Domain");
            return "from";
          }  
        boolean status = Userserv.AddUserDetails(user);
        if (status) {
            System.out.println("Successfully Applied. We will contact soon!");
            model.addAttribute("SucMsg", "Successfully Applied. We will contact soon!");
        } else {
            System.out.println("Apllication Failed!! Try again.");
            model.addAttribute("FailMsg", "Apllication Failed!! Try again.");
        }
        return "from";
    }
    }

