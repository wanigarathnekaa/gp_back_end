package com.example.gp_back_end.Controller;

import com.example.gp_back_end.dto.EmailRequest;
import com.example.gp_back_end.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getSubject(), emailRequest.getText());
        return "Email sent successfully!";
    }
}
