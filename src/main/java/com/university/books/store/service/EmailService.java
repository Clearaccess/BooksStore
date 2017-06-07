package com.university.books.store.service;

/**
 * Created by Aleksandr on 6/7/2017.
 */
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
