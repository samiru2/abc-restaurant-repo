package com.abc.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

    private static final String SENDER_EMAIL = "abcrestaurantsrilanka@outlook.com";
    private static final String SENDER_PASSWORD = "ABCrestaurant123";

    public static void sendEmail(String recipientEmail, String subject, String messageText) throws MessagingException {
        // Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
