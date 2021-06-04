package domain;

import constants.Fixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificacionTest {
  Fixture fixture;
  Notificacion notificacionDuenio, notificacionRescatista;

  @BeforeEach
  public void prepararDatos() {
    fixture = new Fixture();
    notificacionDuenio = new Notificacion(new DuenioContactaRescatistaTemplate(fixture.duenio()));
    notificacionRescatista = new Notificacion(new RescatistaContactaDuenioTemplate(fixture.rescateConChapa()));
  }

//  @Test
//  public void testDuenioNotificaRescatistaPorRescate() {
//    Assertions.assertEquals(
//        "<h1>El due&ntilde;io de la mascota quiere contactar contigo</h1>" +
//            "<br>" +
//            "<p>Juan ha visto la publicaci&oacute;n y quere contactar contigo<br>" +
//            "Estos son sus datos de contacto:</p>" +
//            "<table>" +
//            "<thead>" +
//            "<tr>" +
//            "<th>Nombre</th>" +
//            "<th>Apellido</th>" +
//            "<th>Tel</th>" +
//            "<th>Email</th>" +
//            "<th>V&iacute;nculo</th>" +
//            "</tr>" +
//            "</thead>" +
//            "<tr>" +
//            "<td>Pedro</td>" +
//            "<td>Gonzalez</td>" +
//            "<td>494949</td>" +
//            "<td>pedro@g.com</td>" +
//            "<td>AMISTAD</td>" +
//            "</tr>" +
//            "</table>"
//        , notificacionDuenio.getMensaje());
//  }
//
//  @Test
//  public void testRescatistaContactaConDuenio() {
//    Assertions.assertEquals(notificacionRescatista.getMensaje(),
//        "<h1>Encontramos a tu mascota!</h1>\r\n" +
//        "<br><p>Fue encontrada en: El Rescatista 123</p>\r\n" +
//        "<p>Te dejamos el contacto del rescatista:</p>\r\n" +
//        "Nombre: Pedro\r\n" +
//        "Apellido: Gonzalez\r\n" +
//        "Tel: 494949\r\n" +
//        "Mail: pedro@g.com");
//  }
}
