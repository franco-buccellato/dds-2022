package domain;

import constants.MensajePruebaTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.MailReader;

import javax.mail.MessagingException;
import java.io.IOException;

import static domain.Vinculo.TITULAR;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class ContactoTest {
  Contacto contacto;

  @BeforeEach
  void setup() {
    MedioNotificacion medioNotificacion = mock(MedioNotificacion.class);
    contacto = new Contacto("Juan", "Perez", "11123123123", "juan@perez.com", TITULAR, medioNotificacion);
  }

  @Test
  void puedoLeerUnContacto() {
    assertEquals("Juan", contacto.getNombre());
    assertEquals("Perez", contacto.getApellido());
    assertEquals(TITULAR, contacto.getVinculo());
    assertEquals("juan@perez.com", contacto.getMail());
    assertEquals("11123123123", contacto.getTelefono());
  }

  @Test
  void puedoActualizarDatosDeUnContacto() {
    contacto.setMail("perez@juan.com");
    contacto.setTelefono("321321321");

    assertEquals("Juan", contacto.getNombre());
    assertEquals("Perez", contacto.getApellido());
    assertEquals("perez@juan.com", contacto.getMail());
    assertEquals("321321321", contacto.getTelefono());
  }

  @Test
  void puedoContactarUnContacto() throws IOException, MessagingException {
    String emailTo = "contact.patitas+juan@gmail.com";
    String mensaje = "Mensaje #" + System.currentTimeMillis();
    Notificacion notificacion = new Notificacion(new MensajePruebaTemplate(mensaje));

    contacto.setMedioNotificacion(new MedioNotificacionEmail());
    contacto.setMail(emailTo);
    contacto.notificar(notificacion);

    MailReader reader = new MailReader();
    reader.check();

    assertEquals(reader.getMensaje().get("To"), emailTo);
    assertTrue(reader.getMensaje().get("Content").contains(mensaje));
  }


}