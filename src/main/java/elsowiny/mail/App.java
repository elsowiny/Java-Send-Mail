package elsowiny.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
    public static void main( String[] args )
    {
    	//NOTE
    	//the app may fail due to gmail not allowing "less secure app access"
    	//you may need to turn on access to allow less secure apps to utilize gmail
    	//this can be done with the url below
    	//https://www.google.com/settings/security/lesssecureapps
        	
    		//set your gmailemail
    		//and password
            final String gmailEmail = "@gmail.com";
            final String password = "";
            
            String from = "@gmail.com";
            String recipent = "yahoo.com";
            String subject = "TEST subject";
            String msg = "This message is being sent \n\n"
            		+ "via java";

            Properties prop = new Properties();
    		prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(gmailEmail, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));

                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(recipent)
                );
                message.setSubject(subject);
                message.setText(msg);

                Transport.send(message);

                System.out.println("Email has been Sent.");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        
            
        
       
    }
}
