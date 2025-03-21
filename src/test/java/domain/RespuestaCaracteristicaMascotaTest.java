package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import constants.Fixture;
import domain.exception.SeleccionInvalidaException;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class RespuestaCaracteristicaMascotaTest extends Fixture {

  @Test
  public void noPuedoCrearSeleccionCaracteristicaMascotaSinPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaCaracteristicaMascota(null, opcionesVacunas);
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearSeleccionCaracteristicaMascotaSinopciones() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new RespuestaCaracteristicaMascota(comportamiento, null);
    });

    assertEquals(NOT_NULO.mensaje("selecciones"), exception.getMessage());
  }

  @Test
  public void crearMascotaCaracteristicaConCaracteristicaFueraDeRangoLanzaException() {
    SeleccionInvalidaException exception = assertThrows(SeleccionInvalidaException.class, () -> {
      new RespuestaCaracteristicaMascota(comportamiento, Collections.singletonList(moquillo));
    });

    assertEquals("La opcion seleccionada no está disponible", exception.getMessage());
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaChoiceValida() {
    RespuestaCaracteristicaMascota respuestaCaracteristica = new RespuestaCaracteristicaMascota(
        comportamiento,
        Collections.singletonList(comportamientoTranquilo)
    );

    assertTrue(
        respuestaCaracteristica.getPregunta().getOpciones().contains(comportamientoTranquilo)
    );
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaInput() {
    RespuestaCaracteristicaMascota respuestaCaracteristica = new RespuestaCaracteristicaMascota(
        datosDeInteres,
        Collections.singletonList(new Opcion("Le gusta jugar a la pelota"))
    );

    assertEquals("Le gusta jugar a la pelota",
                 respuestaCaracteristica.getSelecciones().get(0).getDescripcion());
  }
}
