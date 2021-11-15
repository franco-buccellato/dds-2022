package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.SeleccionInvalidaExcepction;
import org.junit.jupiter.api.Test;

import java.util.Collections;

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
    SeleccionInvalidaExcepction exception = assertThrows(SeleccionInvalidaExcepction.class, () -> {
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
        respuestaInteresAdopcion.getPregunta().getOpciones().contains(comportamientoConNinios.getOpciones().get(0))
    );
  }
}
