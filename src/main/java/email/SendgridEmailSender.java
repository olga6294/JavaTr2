package email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendgridEmailSender {

    public void sendEmail(String from, String to, String subject, String content){
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("src/config.properties");
            properties.load(propertiesFile);
            propertiesFile.close();

            SendgridEmail sendgridEmail = new SendgridEmail();

            sendgridEmail.newMail();
            sendgridEmail.setSender(from);
            sendgridEmail.setRecipient(to);
            sendgridEmail.setSubject(subject);
            sendgridEmail.setContent("text/plain", content);

            EmailController emailController = new EmailController(properties.getProperty("SENDGRID_ACCESS_KEY"));
            emailController.send(sendgridEmail);
        }catch(IOException ioe){
            ioe.printStackTrace();
            throw new RuntimeException();
        }
    }
}
