package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DuenioTest extends Fixture {
  Contacto contacto;
  DatoPersonal datoPersonal;
  Duenio duenio;

  @BeforeEach
  void setup() {
    datoPersonal = datoPersonal();
    contacto = contacto();
    duenio = new Duenio(datoPersonal, Arrays.asList(contacto), null, null);
  }

  @Test
  void noPuedoCrearDuenioSiFaltanDatosPersonales() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Duenio(null, Arrays.asList(contacto), null, null);
    });
    assertEquals(NOT_NULO.mensaje("datoPersonal"), exception.getMessage());
  }

  @Test
  void noPuedoCrearDuenioSinContacto() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Duenio(datoPersonal, null, null, null);
    });
    assertEquals(NOT_NULO.mensaje("contactos"), exception.getMessage());
  }

  @Test
  void puedoLeerDatosDeUnDuenio() {
    assertEquals(datoPersonal, duenio.getDatoPersonal());
    assertEquals(contacto, duenio.getContactos().get(0));
    assertEquals(0, duenio.getMascotas().size());
    assertNull(duenio.getUsuario());
  }

  @Test
  void puedoActualizarElContactoDeUnDuenio() throws IOException {
    MedioNotificacion email = new MedioNotificacionEmail();
    Contacto nuevoContacto = new Contacto("Pedro", "Perez", "11123123123", "pedro@perez.com", Vinculo.FAMILIAR, email);
    duenio.setContactos(Arrays.asList(nuevoContacto));

    assertNotEquals(contacto, duenio.getContactos().get(0));
    assertEquals(nuevoContacto, duenio.getContactos().get(0));
  }

  @Test
  void puedoActualizarLaMascotaDeUnDuenio() {
    assertEquals(0, duenio.getMascotas().size());

    Mascota mascota = new Mascota(
        TipoMascota.PERRO,
        "Pepe",
        "Pe",
        4.2,
        Sexo.HEMBRA,
        "Un poco flaco",
        Arrays.asList("unaFoto"),
        null,
        SituacionMascota.EN_HOGAR_PROPIO
    );

    duenio.setMascotas(new ArrayList(Arrays.asList(mascota)));

    assertEquals(Arrays.asList(mascota), duenio.getMascotas());
    assertEquals(1, duenio.getMascotas().size());

    duenio.addMascota(mascota);

    assertEquals(2, duenio.getMascotas().size());
  }
}
