package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.RespuestaInvalidaException;
import org.junit.jupiter.api.Test;

public class PreguntaAdopcionTest extends Fixture {

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new PreguntaAdopcion(null, "Respuesta");
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new PreguntaAdopcion(this.preguntaTamanio(), null);
    });

    assertEquals(NOT_NULO.mensaje("respuesta"), exception.getMessage());
  }

  @Test
  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
    RespuestaInvalidaException exception = assertThrows(RespuestaInvalidaException.class, () -> {
      new PreguntaAdopcion(this.preguntaComportamiento(), "demente");
    });

    assertEquals("La respuesta demente no es valida", exception.getMessage());
  }

  @Test
  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
    PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(
        this.preguntaComportamiento(),
        comportamientoTranquilo.getDescripcion()
    );

    assertTrue(preguntaAdopcion.getPregunta().getOpciones().contains(comportamientoTranquilo));
  }
}
