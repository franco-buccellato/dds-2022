package domain.usuario;

import static constants.Mensajes.NOT_NULO;
import static domain.TipoIdentificacion.*;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DuenioTest {
  Contacto contacto;
  Duenio duenio;

  @BeforeEach
  void setup() {
    contacto = new Contacto("Juan", "Perez", "11123123123", "juan@perez.com", Vinculo.TITULAR);
    duenio = new Duenio("Juan", "Perez", DNI, "123123123", Arrays.asList(contacto), null, LocalDate.of(1990, 1, 1), null);
  }

  @Test
  void puedoCrearUnaPersona() {
    assertNotNull(duenio);
  }

  @Test
  void noPuedoCrearUnaPersonaSiFaltanRequiredParams() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Duenio(null, "Perez", DNI, "123123123", Arrays.asList(contacto), null, LocalDate.of(1990, 1, 1), null);
    });
    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }

  @Test
  void puedoLeerDatosDeUnaPersona() {
    assertEquals("Juan", duenio.getNombre());
    assertEquals("Perez", duenio.getApellido());
    assertEquals(DNI, duenio.getTipoIdentificacion());
    assertEquals("123123123", duenio.getNroIdentificacion());
    assertEquals(null, duenio.getMascotas());
    assertEquals(LocalDate.of(1990, 1, 1), duenio.getFechaNacimiento());
  }

  @Test
  void puedoActualizarElContactoDeUnaPersona() {
    Contacto nuevoContacto = new Contacto("Pedro", "Perez", "11123123123", "pedro@perez.com", Vinculo.FAMILIAR);
    duenio.setContactos(Arrays.asList(nuevoContacto));

    assertNotEquals(contacto, duenio.getContactos().get(0));
    assertEquals(nuevoContacto, duenio.getContactos().get(0));
  }

  @Test
  void puedoActualizarLaMascotaDeUnaPersona() throws IOException {
    Mascota mascota = new Mascota(TipoMascota.PERRO, "Pepe", "Pe", 4.2, Sexo.HEMBRA, "Un poco flaco", new ArrayList<Image>(Arrays.asList(ImageIO.read(new File("resources/images/perro.jpg")))), null, SituacionMascota.EN_HOGAR_PROPIO);
    duenio.setMascotas(new ArrayList(Arrays.asList(mascota)));

    assertEquals(Arrays.asList(mascota), duenio.getMascotas());
    assertEquals(1, duenio.getMascotas().size());

    duenio.addMascota(mascota);

    assertEquals(2, duenio.getMascotas().size());
  }
}
