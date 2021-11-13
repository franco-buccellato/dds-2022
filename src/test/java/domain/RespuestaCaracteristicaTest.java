//package domain;
//
//import static domain.exception.Mensajes.NOT_NULO;
//import static org.junit.jupiter.api.Assertions.*;
//
//import constants.Fixture;
//import domain.exception.RespuestaInvalidaException;
//import org.junit.jupiter.api.Test;
//
//public class RespuestaCaracteristicaTest extends Fixture {
//
//  @Test
//  public void noPuedoCrearMascotaCaracteristicaSinCaracteristica() {
//    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
//      new RespuestaCaracteristica(null, "Respuesta");
//    });
//
//    assertEquals(NOT_NULO.mensaje("caracteristica"), exception.getMessage());
//  }
//
//  @Test
//  public void noPuedoCrearMascotaCaracteristicaSinRespuesta() {
//    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
//      new RespuestaCaracteristica(this.comportamiento(), null);
//    });
//
//    assertEquals(NOT_NULO.mensaje("respuesta"), exception.getMessage());
//  }
//
//  @Test
//  public void crearMascotaCaracteristicaConCaracteristicaFueraDeRangoLanzaException() {
//    RespuestaInvalidaException exception = assertThrows(RespuestaInvalidaException.class, () -> {
//      new RespuestaCaracteristica(this.comportamiento(), "demente");
//    });
//
//    assertEquals("La respuesta demente no es valida", exception.getMessage());
//  }
//
//  @Test
//  public void puedoCrearMascotaCaracteristicaConCaracteristicaChoiceValida() {
//    RespuestaCaracteristica respuestaCaracteristica = new RespuestaCaracteristica(
//        this.comportamiento(),
//        comportamientoTranquilo.getDescripcion()
//    );
//
//    assertTrue(
//        respuestaCaracteristica.getCaracteristica().getOpciones().contains(comportamientoTranquilo)
//    );
//  }
//
//  @Test
//  public void puedoCrearMascotaCaracteristicaConCaracteristicaInput() {
//    RespuestaCaracteristica respuestaCaracteristica = new RespuestaCaracteristica(
//        this.datosDeInteres(),
//        "Le gusta jugar a la pelota"
//    );
//
//    assertEquals("Le gusta jugar a la pelota", respuestaCaracteristica.getRespuesta());
//  }
//}
