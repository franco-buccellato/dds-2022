package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.SeleccionInvalidaExcepction;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class SeleccionCaracteristicaMascotaTest extends Fixture {

  @Test
  public void noPuedoCrearSeleccionCaracteristicaMascotaSinPregunta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new SeleccionCaracteristicaMascota(null, opcionesVacunas);
    });

    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearSeleccionCaracteristicaMascotaSinopciones() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new SeleccionCaracteristicaMascota(this.comportamiento(), null);
    });

    assertEquals(NOT_NULO.mensaje("selecciones"), exception.getMessage());
  }

  @Test
  public void crearMascotaCaracteristicaConCaracteristicaFueraDeRangoLanzaException() {
    SeleccionInvalidaExcepction exception = assertThrows(SeleccionInvalidaExcepction.class, () -> {
      new SeleccionCaracteristicaMascota(this.comportamiento(), Collections.singletonList(moquillo));
    });

    assertEquals("La opcion seleccionada no está disponible", exception.getMessage());
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaChoiceValida() {
    SeleccionCaracteristicaMascota respuestaCaracteristica = new SeleccionCaracteristicaMascota(
        this.comportamiento(),
        Collections.singletonList(comportamientoTranquilo)
    );

    assertTrue(
        respuestaCaracteristica.getPregunta().getOpciones().contains(comportamientoTranquilo)
    );
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaInput() {
    SeleccionCaracteristicaMascota respuestaCaracteristica = new SeleccionCaracteristicaMascota(
        datosDeInteres,
        Collections.singletonList(new Opcion("Le gusta jugar a la pelota"))
    );

    assertEquals("Le gusta jugar a la pelota", respuestaCaracteristica.getSelecciones().get(0).getDescripcion());
  }
}
