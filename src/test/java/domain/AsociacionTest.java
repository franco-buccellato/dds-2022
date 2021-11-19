package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static domain.exception.Mensajes.NOT_NULO;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AsociacionTest extends Fixture {
  Ubicacion ubicacion;

  @BeforeEach
  void setup() {
    super.generalSetup();
    ubicacion = ubicacion1();
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
