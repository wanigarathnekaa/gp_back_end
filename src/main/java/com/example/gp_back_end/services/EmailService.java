package com.example.gp_back_end.services;

import com.example.gp_back_end.model.EmailModel;
import com.example.gp_back_end.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(String subject, String text) {
        List<EmailModel> emailModels = emailRepository.findAll();

        for (EmailModel emailModel : emailModels) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmail());
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }
    }
}
