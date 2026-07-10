package email;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    public static void main (String[] args) {
        final String senderEmail="test150498nim@gmail.com";
        final String senderPassword="vaufsqjajqnymgid";
        final String receiverEmail="test150498nim@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        session.setDebug(true);

        try{
            //Email Created
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("Test Email from Framework Practise");
            message.setText("Test Email \n Email from Framework Practise\n Enjoy");


            //Attaching the report
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is a test email from Framework Practise");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filePath = System.getProperty("user.dir")+"/reports/index.html";
            System.out.println("Report attached from path : "+filePath);
            attachmentPart.attachFile(new File(filePath));

            //Combine body and attachment
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            //Email Sent
            Transport.send(message);
            System.out.println("Email Sent Successfully");



        } catch (AddressException e) {
            System.out.println("Email Sent Failed Address not found");
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            System.out.println("Email Sent Failed");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

