package email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class SendgridEmail {

    private Mail mail;

    public void newMail() {
        mail = new Mail();
    }

    public Mail generateMail(){
        return mail;
    }

    public void setSender(String senderEmail){
        Email from = new Email();

        from.setEmail(senderEmail);
        mail.setFrom(from);
    }

    public void setRecipient(String recipientEmail){
        Email to = new Email();

        to.setEmail(recipientEmail);

        Personalization personalization = new Personalization();

        personalization.addTo(to);

        mail.addPersonalization(personalization);
    }

    public void setSubject(String subject){
        mail.setSubject(subject);
    }

    public void setContent(String type, String content){
        mail.addContent(new Content(type,content));
    }

}
