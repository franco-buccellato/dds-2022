//package domain;
//
//import constants.Fixture;
//import domain.templatesNotificacion.DuenioContactaRescatistaTemplate;
//import domain.templatesNotificacion.PublicacionInteresTemplate;
//import domain.templatesNotificacion.RescatistaContactaDuenioTemplate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//public class NotificacionTest {
//  Fixture fixture;
//  Notificacion notificacionDuenio, notificacionRescatista, notificacionPublicacionInteresAdopcion;
//
//  @BeforeEach
//  public void prepararDatos() {
//    fixture = new Fixture();
//    notificacionDuenio = new Notificacion(new DuenioContactaRescatistaTemplate(fixture.duenio()));
//    //notificacionRescatista = new Notificacion(new RescatistaContactaDuenioTemplate(fixture.rescateConChapa()));
//    notificacionPublicacionInteresAdopcion = new Notificacion(
//        new PublicacionInteresTemplate(
//            "lista_interes",
//            Arrays.asList(
//                fixture.publicacionAdopcion1(),
//                fixture.publicacionAdopcion2(),
//                fixture.publicacionAdopcion3())));
//  }
//
////  @Test
////  public void testDuenioNotificaRescatistaPorRescate() {
////    Assertions.assertEquals(
////        "<h1>El due&ntilde;io de la mascota quiere contactar contigo</h1>" +
////            "<br>" +
////            "<p>Juan ha visto la publicaci&oacute;n y quere contactar contigo<br>" +
////            "Estos son sus datos de contacto:</p>" +
////            "<table>" +
////            "<thead>" +
////            "<tr>" +
////            "<th>Nombre</th>" +
////            "<th>Apellido</th>" +
////            "<th>Tel</th>" +
////            "<th>Email</th>" +
////            "<th>V&iacute;nculo</th>" +
////            "</tr>" +
////            "</thead>" +
////            "<tr>" +
////            "<td>Pedro</td>" +
////            "<td>Gonzalez</td>" +
////            "<td>494949</td>" +
////            "<td>pedro@g.com</td>" +
////            "<td>AMISTAD</td>" +
////            "</tr>" +
////            "</table>".replaceAll("^[\n\r]", "").replaceAll("[\n\r]$", "")
////        , notificacionDuenio.getMensaje().replaceAll("^[\n\r]", "").replaceAll("[\n\r]$", ""));
////  }
//
////  @Test
////  public void testRescatistaContactaConDuenio() {
////    Assertions.assertEquals(notificacionRescatista.getMensaje(),
////        "<h1>Encontramos a tu mascota!</h1>\n" +
////        "<br><p>Fue encontrada en: El Rescatista 123</p>\n" +
////        "<p>Te dejamos el contacto del rescatista:</p>\n" +
////        "Nombre: Pedro\n" +
////        "Apellido: Gonzalez\n" +
////        "Tel: 494949\n" +
////        "Mail: pedro@g.com");
////  }
//
//  @Test
//  public void testPublicacionInteresAdopcionTemplate() {
//    System.out.println(notificacionPublicacionInteresAdopcion.getMensaje());
//  }
//}
