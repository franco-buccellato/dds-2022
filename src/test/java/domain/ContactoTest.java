package domain;

import domain.Contacto;
import domain.Vinculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactoTest {
  Contacto contacto;

  @BeforeEach
  void setup() {
    contacto = new Contacto("Juan", "Perez", "11123123123", "juan@perez.com", Vinculo.TITULAR);
  }

  @Test
  void puedoLeerUnContacto() {
    assertEquals("Juan", contacto.getNombre());
    assertEquals("Perez", contacto.getApellido());
    assertEquals(Vinculo.TITULAR, contacto.getVinculo());
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
}