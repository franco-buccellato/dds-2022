package domain.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactoTest {
  Contacto contacto;

  @BeforeEach
  void setup(){
    contacto = new Contacto("Juan", "Perez", "11123123123", "juan@perez.com", "Calle Falsa 123" );
  }
  @Test
  void puedoCrearUnContacto() {
    assertNotNull(contacto);
  }
  @Test
  void puedoLeerUnContacto() {
    assertEquals("Juan", contacto.getNombre());
    assertEquals("Perez", contacto.getApellido());
    assertEquals("Calle Falsa 123", contacto.getDireccion());
    assertEquals("juan@perez.com",contacto.getMail());
    assertEquals("11123123123",contacto.getTelefono());
  }
  @Test
  void puedoActualizarDatosDeUnContacto() {
    contacto.setDireccion("Calle Verdadera 321");
    contacto.setMail("perez@juan.com");
    contacto.setTelefono("321321321");

    assertEquals("Juan", contacto.getNombre());
    assertEquals("Perez", contacto.getApellido());
    assertEquals("Calle Verdadera 321", contacto.getDireccion());
    assertEquals("perez@juan.com",contacto.getMail());
    assertEquals("321321321",contacto.getTelefono());
  }
}