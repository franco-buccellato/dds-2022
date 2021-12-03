package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import constants.Fixture;
import domain.exception.SeleccionInvalidaException;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class RespuestaInteresAdopcionTest extends Fixture {

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaInteresAdopcion(null, opcionesVacunas);
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaInteresAdopcion(vacunas, null);
    });

    assertEquals(NOT_NULO.mensaje("selecciones"), exception.getMessage());
  }

  @Test
  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
    SeleccionInvalidaException exception = assertThrows(SeleccionInvalidaException.class, () -> {
      new RespuestaInteresAdopcion(vacunas, Collections.singletonList(tamanioGrande));
    });

    assertEquals("La opcion seleccionada no está disponible", exception.getMessage());
  }

  @Test
  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
    RespuestaInteresAdopcion respuestaInteresAdopcion = new RespuestaInteresAdopcion(
        comportamientoConNinios,
        Collections.singletonList(comportamientoConNinios.getOpciones().get(0))
    );

    assertTrue(
        respuestaInteresAdopcion.getPregunta()
            .getOpciones()
            .contains(comportamientoConNinios.getOpciones().get(0))
    );
  }
}
