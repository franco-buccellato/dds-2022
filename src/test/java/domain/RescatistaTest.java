package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RescatistaTest {
  DatoPersonal datoPersonal;
  Contacto contacto;
  Ubicacion ubicacion;
  Rescatista rescatista;

  @BeforeEach
  public void setup() {
    Fixture fixture = new Fixture();
    datoPersonal = fixture.datoPersonal();
    contacto = fixture.contacto();
    ubicacion = fixture.ubicacion();
    rescatista = fixture.rescatista();
  }

  @Test
  void noPuedoCrearRescatistaSinDatoPersonal() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescatista(null, contacto, ubicacion);
    });
    assertEquals(NOT_NULO.mensaje("datoPersonal"), exception.getMessage());
  }

  @Test
  void noPuedoCrearRescatistaSinContacto() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescatista(datoPersonal, null, ubicacion);
    });
    assertEquals(NOT_NULO.mensaje("contacto"), exception.getMessage());
  }

  @Test
  void noPuedoCrearRescatistaSinUbicacion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescatista(datoPersonal, contacto, null);
    });
    assertEquals(NOT_NULO.mensaje("ubicacion"), exception.getMessage());
  }
}
