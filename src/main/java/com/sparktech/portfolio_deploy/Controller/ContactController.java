package com.sparktech.portfolio_deploy.Controller;

import com.sparktech.portfolio_deploy.Model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("contact-form")
    public String contactForm(@ModelAttribute Contact contact) {
        log.info("This is the Contact form{}", contact);
        return "redirect:/contact";

    }
}
