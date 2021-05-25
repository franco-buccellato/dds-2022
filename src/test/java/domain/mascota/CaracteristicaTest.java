package domain.mascota;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import domain.Caracteristica;
import domain.TipoCaracteristica;
import domain.TipoCaracteristicaTextoLibre;
import org.junit.jupiter.api.Test;

public class CaracteristicaTest {
  @Test
  public void noPuedoCrearUnaCaracteristicaSinTipoCaracteristica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Caracteristica(null, "verde");
    });

    assertEquals(NOT_NULO.mensaje("tipoCaracteristica"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearUnaCaracteristicaSinDescripcion() {
    TipoCaracteristica colorSecundario = new TipoCaracteristicaTextoLibre("Color Secundario");

    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Caracteristica(colorSecundario, null);
    });

    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }
}
