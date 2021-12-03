package domain;

import domain.exception.NoSePudoEnviarMailException;
import domain.exception.NoSePuedenObtenerEmailCredentials;
import domain.repositorios.RepositorioDuenio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MedioNotificacionEmail implements MedioNotificacion {
  private Properties emailCredentials;
  private static MedioNotificacionEmail INSTANCE = new MedioNotificacionEmail();

  public MedioNotificacionEmail() {
    emailCredentials = new Properties();
    try {
      emailCredentials.loadFromXML(new FileInputStream("resources/email/credentials.xml"));
    } catch (IOException e) {
      throw new NoSePuedenObtenerEmailCredentials(e.getMessage());
    }
  }

  public static MedioNotificacionEmail getInstance() {
    return INSTANCE;
  }

  @Override
  public void notificar(Contacto contacto, String mensage)  {
    try {
      sendEmail(contacto.getMail(), "Contacto desde Patitas por sus mascota", mensage);
    } catch (MessagingException e) {
      throw new NoSePudoEnviarMailException(e.getMessage());
    }
  }

  private Session getSession() {
    return Session.getInstance(getSmtpProperties(), new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("contact.patitas@gmail.com", "P@sswOrd1!T3st");
      }
    });
  }

  private Properties getSmtpProperties() {
    Properties smtpProperties = new Properties();
    smtpProperties.put("mail.smtp.auth", "true");
    smtpProperties.put("mail.smtp.starttls.enable", "true");
    smtpProperties.put("mail.smtp.host", "smtp.gmail.com");
    smtpProperties.put("mail.smtp.port", "587");
    return smtpProperties;
  }

  public void sendEmail(String contacto, String asunto, String mensage) throws MessagingException {
    MimeMessage message = new MimeMessage(getSession());
    message.setFrom(new InternetAddress("contact.patitas+admin@gmail.com"));
    message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse(contacto));
    message.setSubject(asunto);
    message.setText(mensage);
    Transport.send(message);
  }
}