package com.university.books.store.service.impl;

import com.university.books.store.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aleksandr on 6/7/2017.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("aleksandr-vanykov@yandex.ru");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
