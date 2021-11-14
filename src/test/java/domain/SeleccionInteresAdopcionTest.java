package domain;

import static domain.AlcanceRespuesta.PUBLICACION_INTERES_ADOPCION_COMODIDAD;
import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.SeleccionInvalidaExcepction;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class SeleccionInteresAdopcionTest extends Fixture {

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new SeleccionInteresAdopcion(null, opcionesVacunas, PUBLICACION_INTERES_ADOPCION_COMODIDAD);
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new SeleccionInteresAdopcion(vacunas, null, PUBLICACION_INTERES_ADOPCION_COMODIDAD);
    });

    assertEquals(NOT_NULO.mensaje("selecciones"), exception.getMessage());
  }

  @Test
  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
    SeleccionInvalidaExcepction exception = assertThrows(SeleccionInvalidaExcepction.class, () -> {
      new SeleccionInteresAdopcion(vacunas, Collections.singletonList(tamanioGrande), PUBLICACION_INTERES_ADOPCION_COMODIDAD);
    });

    assertEquals("La opcion seleccionada no está disponible", exception.getMessage());
  }

  @Test
  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
    SeleccionInteresAdopcion respuestaInteresAdopcion = new SeleccionInteresAdopcion(
        comportamientoConNinios,
        Collections.singletonList(comportamientoConNinios.getOpciones().get(0)),
        PUBLICACION_INTERES_ADOPCION_COMODIDAD
    );

    assertTrue(
        respuestaInteresAdopcion.getPregunta().getOpciones().contains(comportamientoConNinios.getOpciones().get(0))
    );
  }
}
