package domain;

import domain.exception.NoSePudoEnviarMailException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.MessagingException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MedioNotificacionEmail implements MedioNotificacion {
  private Properties emailCredentials;

  public MedioNotificacionEmail() throws IOException {
    emailCredentials = new Properties();
    emailCredentials.loadFromXML(new FileInputStream("resources/email/credentials.xml"));
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
          return new PasswordAuthentication(emailCredentials.getProperty("usuarioContacto"),
              emailCredentials.getProperty("passContacto")
          );
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
    message.setFrom(new InternetAddress(emailCredentials.getProperty("usuarioAdmin")));
    message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse(contacto));
    message.setSubject(asunto);
    message.setText(mensage);
    Transport.send(message);
  }
}