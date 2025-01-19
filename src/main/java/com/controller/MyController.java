package com.controller;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.services.UserService;
import com.user.User;

import jakarta.mail.internet.MimeMessage;
import java.util.logging.Logger;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    private final JavaMailSender mailSender;

    @Autowired
    public MyController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/user_apply")
    public String getApplyForm(Model model) {
        model.addAttribute("user", new User());
        return "Apply"; // Return form.jsp
    }

    @GetMapping("/User_Contact")
    public String getContactPage() {
        return "contact"; // Return contact.jsp
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/index.html"; // Redirect to index.html
    }

    @GetMapping("/BackToContact")
    public String redirectToContact() {
        return "contact"; // Return contact.jsp
    }
    @GetMapping("/policy")
    public String redirectToPolicy() {
        return "Policy"; // Return Policy.jsp
    }

    @PostMapping("/addinfo")
public String saveUserRegistration(@ModelAttribute("user") User user, Model model) {
    try {
        logger.info("User application attempt: " + user.getEmail());

        if (userService.IsEmailDomainReg(user.getEmail(), user.getDomain())) {
            model.addAttribute("FailMsg", "Email is already applied with the same domain.");
            logger.warning("Duplicate application attempt detected for: " + user.getEmail());
            return "Apply";
        }

        boolean status = userService.AddUserDetails(user);
        if (status) {
            model.addAttribute("SucMsg", "Successfully Applied. We will contact you soon!");

            new Thread(() -> {
                try {
                    sendUserEmail(user);
                    sendAdminNotification(user);
                } catch (Exception e) {
                    logger.severe("Error sending emails asynchronously: " + e.getMessage());
                }
            }).start();
        } else {
            model.addAttribute("FailMsg", "Application Failed! Try again.");
            logger.warning("User application failed for: " + user.getEmail());
        }
    } catch (Exception e) {
        model.addAttribute("FailMsg", "An unexpected error occurred. Please try again.");
        logger.severe("Unexpected error during user registration: " + e.getMessage());
    }

    return "Apply";
}


    private static final Logger logger = Logger.getLogger(MyController.class.getName());


    @Async
    private void sendUserEmail(User user) {
        try {
            logger.info("Attempting to send confirmation email to: " + user.getEmail());
    
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
            helper.setFrom("educareithub2024@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Thank you for your application to Educare Intern Technology");
    
            String emailContent = getEmailContent("/templates/email-content.jsp", user.getName(), user.getDomain());
            helper.setText(emailContent, true);
    
            mailSender.send(message);
            logger.info("Confirmation email sent to user: " + user.getEmail());
    
        } catch (Exception e) {
            logger.severe("Error sending confirmation email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getEmailContent(String filePath, String candidateName, String domain) throws Exception {
        String content;
        try (var inputStream = Objects.requireNonNull(MyController.class.getResourceAsStream(filePath))) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error reading email content file: " + e.getMessage());
            throw new Exception("Failed to load email template");
        }
        return content.replace("${Candidatename}", candidateName)
                      .replace("${InternshipProgramName}", domain);
    }

    private void sendAdminNotification(User user) {
        try {
            logger.info("Sending admin notification for new application: " + user.getEmail());
    
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("educareintern.technology@gmail.com");
            message.setTo("info@educareinterntechnology.in");
            message.setSubject("New User Application - Educare Internship Program");
            message.setText("New applicant:\nName: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPhone: " + user.getMobile());
    
            mailSender.send(message);
            logger.info("Notification email sent to admin.");
    
        } catch (Exception e) {
            logger.severe("Error sending admin notification email: " + e.getMessage());
        }
    }
    

    @PostMapping("/send-contact-message")
    public String sendContactMessage(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            Model model) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("info@educareinterntechnology.in");
            mailMessage.setSubject("New Contact Form Submission from " + name);
            mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

            mailSender.send(mailMessage);
            model.addAttribute("success", "Message sent successfully!");
        } catch (MailException e) {
            model.addAttribute("error", "Failed to send message. Please try again.");
            System.err.println("Error sending contact message: " + e.getMessage());
            e.printStackTrace();
        }

        return "contact";
    }


    @ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error-page"; // Name of your error page
    }
}

}
