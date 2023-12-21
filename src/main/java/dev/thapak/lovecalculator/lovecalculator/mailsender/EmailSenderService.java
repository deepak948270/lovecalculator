package dev.thapak.lovecalculator.lovecalculator.mailsender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // sending simple email without attachment
    public void sendEmail(String toEmail, String bodyText) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("deepaksharma948270@gmail.com");
        message.setTo(toEmail);
        message.setText(bodyText);
        message.setSubject(" congratulations Result Declared !");

        // sending result to the email
        javaMailSender.send(message);

        // logged the sucessfull message
        log.info("simple mail send sucessfully");

    }

}
