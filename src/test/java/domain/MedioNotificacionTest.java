package domain;

import static domain.Vinculo.TITULAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import services.MailReader;

public class MedioNotificacionTest {
  Contacto contacto;
  String mensaje;
  String telefono;
  String emailTo;

  @BeforeEach
  public void setup() {
    mensaje = "Mensaje #" + System.currentTimeMillis();
    telefono = String.valueOf(System.currentTimeMillis());
    emailTo = "contact.patitas+" + System.currentTimeMillis() + "@gmail.com";
    contacto = new Contacto("Patitas", "Test", telefono, emailTo, TITULAR);
  }

  @Test
  public void puedoEnviarUnMail() throws MessagingException, IOException {
    MedioNotificacion email = MedioNotificacionEmail.getInstance();
    // Envio email
    email.notificar(contacto, mensaje);
    // Leo ultimo email recibido
    MailReader reader = new MailReader();
    reader.check();

    assertEquals(reader.getMensaje().get("To"), emailTo);
    assertTrue(reader.getMensaje().get("Content").contains(mensaje));
  }
}
