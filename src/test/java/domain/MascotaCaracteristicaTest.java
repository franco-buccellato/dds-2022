package domain;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.exception.RespuestaInvalidaException;
import org.junit.jupiter.api.Test;

public class MascotaCaracteristicaTest extends Fixture {

  @Test
  public void noPuedoCrearMascotaCaracteristicaSinCaracteristica() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaCaracteristica(null, "Respuesta");
    });

    assertEquals(NOT_NULO.mensaje("caracteristica"), exception.getMessage());
  }

  @Test
  public void noPuedoCrearMascotaCaracteristicaSinRespuesta() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new MascotaCaracteristica(this.comportamiento(), null);
    });

    assertEquals(NOT_NULO.mensaje("respuesta"), exception.getMessage());
  }

  @Test
  public void crearMascotaCaracteristicaConCaracteristicaFueraDeRangoLanzaException() {
    RespuestaInvalidaException exception = assertThrows(RespuestaInvalidaException.class, () -> {
      new MascotaCaracteristica(this.comportamiento(), "demente");
    });

    assertEquals("La respuesta demente no es valida", exception.getMessage());
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaChoiceValida() {
    MascotaCaracteristica mascotaCaracteristica = new MascotaCaracteristica(
        this.comportamiento(),
        comportamientoTranquilo.getDescripcion()
    );

    assertTrue(
        mascotaCaracteristica.getCaracteristica().getOpciones().contains(comportamientoTranquilo)
    );
  }

  @Test
  public void puedoCrearMascotaCaracteristicaConCaracteristicaInput() {
    MascotaCaracteristica mascotaCaracteristica = new MascotaCaracteristica(
        this.datosDeInteres(),
        "Le gusta jugar a la pelota"
    );

    assertEquals("Le gusta jugar a la pelota", mascotaCaracteristica.getRespuesta());
  }
}
