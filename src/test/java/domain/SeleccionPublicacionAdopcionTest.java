package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.SeleccionInvalidaExcepction;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class SeleccionPublicacionAdopcionTest extends Fixture {

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaPublicacionAdopcion(null, Collections.singletonList(moquillo));
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaPublicacionAdopcion(tamanio, null);
    });

    assertEquals(NOT_NULO.mensaje("selecciones"), exception.getMessage());
  }

  @Test
  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
    SeleccionInvalidaExcepction exception = assertThrows(SeleccionInvalidaExcepction.class, () -> {
      new RespuestaPublicacionAdopcion(tamanio, Collections.singletonList(moquillo));
    });

    assertEquals("La opcion seleccionada no está disponible", exception.getMessage());
  }

  @Test
  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
    RespuestaPublicacionAdopcion respuestaPublicacionAdopcion = new RespuestaPublicacionAdopcion(
        tamanio,
        Collections.singletonList(tamanioChico)
    );

    assertTrue(respuestaPublicacionAdopcion.getPregunta().getOpciones().contains(tamanioChico));
  }
}
