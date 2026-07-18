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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class ContactController {

    private final JavaMailSender mailSender;

    @Value("${contact.recipient.email}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public ContactController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/contact-form")
    public String contactForm(@ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
        log.info("Contact form submission received: {}", contact);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(recipientEmail);
            message.setSubject("New portfolio contact form message from " + contact.getName());
            message.setText(
                    "Name: " + contact.getName() + "\n" +
                            "Email: " + contact.getEmail() + "\n\n" +
                            "Message:\n" + contact.getMessage()
            );
            message.setReplyTo(contact.getEmail());

            mailSender.send(message);
            log.info("Contact form email sent successfully for {}", contact.getEmail());
            return "redirect:/contact?sent=true";
        } catch (Exception e) {
            log.error("Failed to send contact form email", e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage() != null ? e.getMessage() : e.toString());
            return "redirect:/contact?sent=false";
        }
    }
}