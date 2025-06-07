package com.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendMailWithAttachment(String to,
                                       String subject,
                                       String text,
                                       MultipartFile multipartFile) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true = multipart

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);

        mailSender.send(message);
    }

}
