package ui.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

/**
 * EmailUtils class provides utility methods for sending emails with attachments.
 * It uses the JavaMail API to configure and send emails.
 */
public class EmailUtils {

    /**
     * Sends an email with an attachment to the specified recipient.
     * 
     * @param toEmail Recipient's email address.
     * @param subject Subject of the email.
     * @param body Body of the email.
     * @param attachmentPath Path to the file to be attached.
     */
    public static void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) {

        // Sender’s email credentials (use app password if using Gmail)
        final String fromEmail = "mamyala21@gmail.com"; 
        final String password = "kbwb tkpi nhhb lvbg"; // Never use your real password in code

        // Set up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP host
        props.put("mail.smtp.port", "587"); // TLS Port - common for outgoing SMTP with STARTTLS
        props.put("mail.smtp.auth", "true"); // enable authentication (username/password)
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS (secure the connection)
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create session with authentication - this session will be used to build and send the message
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // supplies the username and password when the SMTP server asks for authentication
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a new email message object (MIME)
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail)); // set the "From" header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // set recipients
            message.setSubject(subject); // subject line

            // Create the body part for the message (the textual part)
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body); // set plain text body

            // Create the attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File file = new File(attachmentPath);
            attachmentPart.attachFile(file); // attach the file (zipped file in this use-case)

            // Combine body and attachment into a multipart container
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart); // add the text part
            multipart.addBodyPart(attachmentPart);  // add the attachment part

            message.setContent(multipart); // set the email content to be the multipart object

            // Send the message using Transport (this connects to SMTP server and sends)
            Transport.send(message);
            System.out.println("✅ Email sent successfully with attachment!");

        } catch (Exception e) {
            e.printStackTrace(); // print error if anything goes wrong (network, auth, file not found)
        }
    }

}