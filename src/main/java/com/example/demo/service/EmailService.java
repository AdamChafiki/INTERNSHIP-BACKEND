package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendVerificationEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        emailSender.send(message);
    }

//    new
public void sendInternshipRequestEmail(String to, String seekerName, String seekerEmail, String seekerMessage, File cv) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setTo(to);
    helper.setSubject("Internship Request from " + seekerName);
    helper.setText(
            "Dear Hiring Manager,<br><br>" +
                    seekerMessage + "<br><br>" +
                    "Regards,<br>" + seekerName + "<br>" +
                    "Email: " + seekerEmail,
            true
    );

    // Add CV as an attachment
    helper.addAttachment(cv.getName(), cv);

    emailSender.send(message);
}
}