package email;

import configproperties.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendgridEmailSender {

    private ConfigProperties configProperties;

    public SendgridEmailSender(ConfigProperties configProperties){
        this.configProperties = configProperties;
    }

    public void sendEmail(String from, String to, String subject, String content){

        SendgridEmail sendgridEmail = new SendgridEmail();

        sendgridEmail.newMail();
        sendgridEmail.setSender(from);
        sendgridEmail.setRecipient(to);
        sendgridEmail.setSubject(subject);
        sendgridEmail.setContent("text/plain", content);

        EmailController emailController = new EmailController(configProperties.getProperty("SENDGRID_ACCESS_KEY"));
        emailController.send(sendgridEmail);

    }
}
