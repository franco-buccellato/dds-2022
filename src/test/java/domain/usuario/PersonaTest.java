package domain.usuario;

import static domain.exception.Mensajes.NOT_NULO;
import static domain.usuario.TipoIdentificacion.*;

import domain.mascota.TipoMascota;
import domain.mascota.Mascota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class PersonaTest {
  Contacto contacto;
  Persona persona;
  @BeforeEach
  void setup(){
    contacto = new Contacto("Juan", "Perez", "11123123123", "juan@perez.com", "Calle Falsa 123" );
    persona = new Persona("Juan", "Perez", DNI, "123123123", contacto, null, LocalDate.of(1990, 1, 1));
    }
  @Test
  void puedoCrearUnaPersona(){
    assertNotNull(persona);
  }
  @Test
  void noPuedoCrearUnaPersonaSiFaltanRequiredParams(){
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Persona(null, "Perez", DNI, "123123123", contacto, null, LocalDate.of(1990, 1, 1));
    });
    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }
  @Test
  void puedoLeerDatosDeUnaPersona(){
    assertEquals("Juan", persona.getNombre());
    assertEquals("Perez", persona.getApellido());
    assertEquals(DNI, persona.getTipoIdentificacion());
    assertEquals("123123123", persona.getNroIdentificacion());
    assertEquals(null, persona.getMascotas());
    assertEquals(LocalDate.of(1990, 1, 1), persona.getFechaNacimiento());
  }
  @Test
  void puedoActualizarElContactoDeUnaPersona() {
    Contacto nuevoContacto = new Contacto("Pedro", "Perez", "11123123123", "pedro@perez.com", "Calle Verdadera 123" );
    persona.setContacto(nuevoContacto);

    assertNotEquals(contacto, persona.getContacto());
    assertEquals(nuevoContacto, persona.getContacto());
  }
  @Test
  void puedoActualizarLaMascotaDeUnaPersona() throws IOException {
    Mascota mascota = new Mascota(TipoMascota.PERRO, "Pepe", "Pe", 4.2, "Femenino", "Un poco flaco", new ArrayList(Arrays.asList(ImageIO.read(new File("resources/images/perro.jpg")))), null);
    persona.setMascotas(new ArrayList(Arrays.asList(mascota)));

    assertEquals(Arrays.asList(mascota), persona.getMascotas());
    assertEquals(1, persona.getMascotas().size());

    persona.addMascota(mascota);

    assertEquals(2, persona.getMascotas().size());
  }
}
