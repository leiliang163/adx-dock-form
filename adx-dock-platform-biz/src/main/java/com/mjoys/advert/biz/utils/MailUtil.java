package com.mjoys.advert.biz.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {

    static final String EMAIL_HOST     = "smtp.126.com";
    static final String EMAIL_USERNAME = "mjoys_tr@126.com";
    static final String EMAIL_PASSWORD = "djmnhdhkgeeylggy";

    /**
     * 邮件内容以html格式发送
     * 
     * @param email
     * @param title
     * @param htmlContent
     */
    public static void sendEmailHtml(String email, String title, String htmlContent) {
        String host = EMAIL_HOST;
        String from = EMAIL_USERNAME;
        String to = email;
        final String sendusername = EMAIL_USERNAME;
        final String sendpassword = EMAIL_PASSWORD;

        Properties props = System.getProperties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendusername, sendpassword);
            }
        });
        try {
            // 定义邮件信息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setContent(htmlContent, "text/html;charset=gb2312");
            // 发送消息
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮件内容以html格式发送
     * 
     * @param emails
     * @param title
     * @param htmlContent
     */
    public static void sendEmailsHtml(String[] emails, String title, String htmlContent) {
        String host = EMAIL_HOST;
        String from = EMAIL_USERNAME;
        final String sendusername = EMAIL_USERNAME;
        final String sendpassword = EMAIL_PASSWORD;

        Properties props = System.getProperties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendusername, sendpassword);
            }
        });
        try {
            // 定义邮件信息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            for (String mail : emails) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            }
            message.setSubject(title);
            message.setContent(htmlContent, "text/html;charset=UTF-8");
            // 发送消息
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
