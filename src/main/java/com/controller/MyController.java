package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.services.UserService;
import com.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MyController {

    @Autowired
    UserService Userserv;

    private final JavaMailSender mailSender;

    @Autowired
    public MyController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/user_apply")
    public String getMethodName(Model model) {
        model.addAttribute("user", new User());
        return "form"; // Returns form.jsp
    }

    @PostMapping("/addinfo")
    public String SaveUserReg(@ModelAttribute("user") User user, Model model) {
        if (Userserv.IsEmailDomainReg(user.getEmail(), user.getDomain())) {
            model.addAttribute("FailMsg", "Email is already applied with the same domain");
            return "form";
        }

        boolean status = Userserv.AddUserDetails(user);
        if (status) {
            System.out.println("Successfully Applied. We will contact soon!");
            model.addAttribute("SucMsg", "Successfully Applied. We will contact soon!");
            SendEmail(user); // Call the SendEmail method to send the confirmation email
            SendEAdminmail(user); // Notify admins about the new application
        } else {
            System.out.println("Application Failed! Try again.");
            model.addAttribute("FailMsg", "Application Failed! Try again.");
        }
        return "form";
    }
//mail send to user 
    public void SendEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("devildeb27@gmail.com"); // Your email address
            message.setTo(user.getEmail()); // Recipient's email address
            message.setSubject("Educare Internship Program");
            message.setText("Thanks for applying for the internship at Educare!");
            mailSender.send(message);
            System.out.println("Mail sent to: " + user.getEmail());
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    //mail send to Admin
    public void SendEAdminmail(User user) {
      try {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setFrom("devildeb27@gmail.com"); // Your email address
          message.setTo("debmalyapan4@gmail.com"); // Recipient's email address
          message.setSubject("Educare Internship Program");
          message.setText("New User appiled"+user.getName()+" ,Email: "+ user.getEmail()+ " ,Phone: "+ user.getMobile());
          mailSender.send(message);
          System.out.println("Mail sent to admins");
      } catch (Exception e) {
          System.err.println("Error sending email: " + e.getMessage());
      }
  }
}
