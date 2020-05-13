package ru.itis.springbootrest.service;

public interface EmailService {
    void sendMail(String subject, String text, String email);
}
