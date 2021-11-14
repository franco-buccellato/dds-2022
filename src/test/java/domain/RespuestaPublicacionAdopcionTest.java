//package domain;
//
//import static domain.exception.Mensajes.NOT_NULO;
//import static org.junit.jupiter.api.Assertions.*;
//
//import constants.Fixture;
//import domain.exception.RespuestaInvalidaException;
//import org.junit.jupiter.api.Test;
//
//public class RespuestaPublicacionAdopcionTest extends Fixture {
////
////  @Test
////  public void noPuedoCrearPreguntasInteresAdopcionSinUnaPregunta() {
////    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
////      new RespuestaPublicacionAdopcion(null, "Respuesta");
////    });
//
////    assertEquals(NOT_NULO.mensaje("pregunta"), exception.getMessage());
////  }
//
//  @Test
//  public void noPuedoCrearPreguntasInteresAdopcionSinUnaRespuesta() {
//    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
//      new RespuestaPublicacionAdopcion(this.preguntaTamanio(), null);
//    });
//
//    assertEquals(NOT_NULO.mensaje("respuesta"), exception.getMessage());
//  }
//
////  @Test
////  public void crearPreguntasInteresAdopcionConRespuestaFueraDeRangoLanzaExcepcion() {
////    RespuestaInvalidaException exception = assertThrows(RespuestaInvalidaException.class, () -> {
////      new RespuestaPublicacionAdopcion(this.preguntaComportamiento(), "demente");
////    });
////
////    assertEquals("La respuesta demente no es valida", exception.getMessage());
////  }
//
////  @Test
////  public void puedoCrearPreguntaInteresAdopcionConPreguntaValida() {
////    RespuestaPublicacionAdopcion respuestaPublicacionAdopcion = new RespuestaPublicacionAdopcion(
////        this.preguntaComportamiento(),
////        comportamientoTranquilo.getDescripcion()
////    );
////
////    assertTrue(respuestaPublicacionAdopcion.getPregunta().getOpciones().contains(comportamientoTranquilo));
////  }
//}
