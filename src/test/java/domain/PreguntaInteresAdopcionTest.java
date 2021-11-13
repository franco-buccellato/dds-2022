package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.RespuestaInvalidaException;
import org.junit.jupiter.api.Test;

public class PreguntaInteresAdopcionTest extends Fixture {

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new PreguntaInteresAdopcion(null, "Respuesta");
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new PreguntaInteresAdopcion(this.preguntaTamanio(), null);
    });

    assertEquals(NOT_NULO.mensaje("respuesta"), exception.getMessage());
  }

  @Test
  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
    RespuestaInvalidaException exception = assertThrows(RespuestaInvalidaException.class, () -> {
      new PreguntaInteresAdopcion(this.preguntaComportamiento(), "demente");
    });

    assertEquals("La respuesta demente no es valida", exception.getMessage());
  }

  @Test
  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
    PreguntaInteresAdopcion preguntaInteresAdopcion = new PreguntaInteresAdopcion(
        this.preguntaComportamiento(),
        comportamientoTranquilo.getDescripcion()
    );

    assertTrue(
        preguntaInteresAdopcion.getPregunta().getOpciones().contains(comportamientoTranquilo)
    );
  }
}
