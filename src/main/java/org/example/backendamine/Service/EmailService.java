package org.example.backendamine.Service;

import org.example.backendamine.Entities.User;
public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
    void sendEmailWithTemplate(User user);
}
