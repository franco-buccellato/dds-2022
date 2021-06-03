package services;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class MailReader {
  private Map<String, String> mensaje = new HashMap<>();
  public void check() {
    try {
      Properties emailCredentials = new Properties();
      emailCredentials.loadFromXML(new FileInputStream("resources/email/credentials.xml"));

      Session emailSession = Session.getDefaultInstance(
          getPopProperties()
      );

      Store store = emailSession.getStore("pop3s");
      store.connect("smtp.gmail.com", emailCredentials.getProperty("usuarioContacto"), emailCredentials.getProperty("passContacto"));
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      Message[] mensajes = emailFolder.getMessages();
      mensaje.put("To", mensajes[mensajes.length - 1].getAllRecipients()[0].toString());
      mensaje.put("Content", mensajes[mensajes.length - 1].getContent().toString());

      emailFolder.close(true);
      store.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static Properties getPopProperties() {
    Properties properties = new Properties();
    properties.put("mail.pop3.host", "smtp.gmail.com");
    properties.put("mail.pop3.port", "995");
    properties.put("mail.pop3.starttls.enable", "true");

    return properties;
  }

  public Map<String, String> getMensaje() {
    return this.mensaje;
  }
}