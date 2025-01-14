package com.controller;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.services.UserService;
import com.user.User;

import jakarta.mail.internet.MimeMessage;

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
    // public void SendEmail(User user) {
    //     try {
    //         MimeMessage message = mailSender.createMimeMessage();

    //         MimeMessageHelper helper = new MimeMessageHelper(message, true);
    //        helper.setFrom("educareithub2024@gmail.com"); // Your official email address
    //        helper.setTo(user.getEmail()); // Recipient's email address
    //        helper.setSubject("Educare Internship Program");
    //       try (var inputStream = Objects.requireNonNull(MyController.class.getResourceAsStream("/templates/email-content.jsp"))) {
    //             helper.setText(
    //                     new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
    //                     true
    //             );
    //     } catch (Exception e) {
    //         System.err.println("Error sending email: " + e.getMessage());
    //     }
    //     mailSender.send(message);
    //     } catch (Exception e) {
    //         System.err.println("Error sending email: " + e.getMessage());
    //     }
    //     }
    


    public void SendEmail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
            helper.setFrom("educareithub2024@gmail.com"); // official email address
            helper.setTo(user.getEmail()); // Recipient's email address
            helper.setSubject("Thank you for your apllication to Educare Intern Technology");
    
            String emailContent = getEmailContent("/templates/email-content.jsp", user.getName(), user.getDomain());
            helper.setText(emailContent, true); // Set email content as HTML
    
            mailSender.send(message);
            System.out.println("Mail sent to user: " + user.getEmail());
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
    
    private String getEmailContent(String filePath, String candidateName, String domain) throws Exception {
        String content;
        try (var inputStream = Objects.requireNonNull(MyController.class.getResourceAsStream(filePath))) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
        // Replace the placeholder with the candidate's name
       content= content.replace("${Candidatename}", candidateName);
        content = content.replace("${InternshipProgramName}", domain);
        return content;
    }
    


    //mail send to Admin
    public void SendEAdminmail(User user) {
      try {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setFrom("educareithub2024@gmail.com "); // Your official email address
          message.setTo("debmalyapan4@gmail.com"); // Recipient's admin email address
          message.setSubject("Educare Internship Program");
          message.setText("New User appiled"+user.getName()+" ,Email: "+ user.getEmail()+ " ,Phone: "+ user.getMobile());
          mailSender.send(message);
          System.out.println("Mail sent to admins");
      } catch (Exception e) {
          System.err.println("Error sending email: " + e.getMessage());
      }
  }
}
