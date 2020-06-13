//package service;
//import com.sun.mail.smtp.SMTPTransport;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Date;
//import java.util.Properties;
//
//
//public class Mail {
//
//        private static final String SMTP_SERVER = "smtp server ";
//        private static final String USERNAME = "";
//        private static final String PASSWORD = "";
//
//        private static final String EMAIL_FROM = "From@gmail.com";
//        private static final String EMAIL_TO = "email_1@yahoo.com, email_2@gmail.com";
//        private static final String EMAIL_TO_CC = "";
//
//        private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
//        private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";
//
//        public static void main(String[] args) {
//
//            final String username = "10176955@redbridge.ac.uk";
//            final String password = "290798is1";
//
//            Properties prop = new Properties();
////            prop.put("mail.smtp.host", "smtp.gmail.com");
////            prop.put("mail.smtp.port", "587");
////            prop.put("mail.smtp.auth", "true");
//            prop.put("mail.smtp.starttls.enable", "true"); //TLS
//            prop.put("mail.smtp.host", "smtp.gmail.com");
//            prop.put("mail.smtp.port", "465");
//            prop.put("mail.smtp.ssl.enable", "true");
//            prop.put("mail.smtp.auth", "true");
//
//            Session session = Session.getInstance(prop,
//                    new javax.mail.Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(username, password);
//                        }
//                    });
//
//            try {
//
//                Message message = new MimeMessage(session);
//                message.setFrom(new InternetAddress("10176955@redbridge.ac.uk"));
//                message.setRecipients(
//                        Message.RecipientType.TO,
//                        InternetAddress.parse("to_username_a@gmail.com, to_username_b@yahoo.com")
//                );
//                message.setSubject("Testing Gmail TLS");
//                message.setText("Dear Mail Crawler,"
//                        + "\n\n Please do not spam my email!");
//
//                Transport.send(message);
//
//                System.out.println("Done");
//
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
