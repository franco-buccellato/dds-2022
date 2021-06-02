package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static domain.exception.Mensajes.NOT_NULO;

public class AsociacionTest {
  Ubicacion ubicacion;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    ubicacion = fixture.ubicacion1();
  }

  @Test
  void siIntentoCrearAsociacionSinNombreacionUnaExcepcionEsLanzada() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Asociacion(null, ubicacion);
    });
    assertEquals(NOT_NULO.mensaje("nombre"), exception.getMessage());
  }

  @Test
  void siIntentoCrearAsociacionSinUbicacionUnaExcepcionEsLanzada() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Asociacion("Asociacion", null);
    });
    assertEquals(NOT_NULO.mensaje("ubicacion"), exception.getMessage());
  }
}
