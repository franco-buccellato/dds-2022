//package domain;
//
//import constants.Fixture;
//import domain.exception.PreguntaObligatoriaNoContestadaException;
//import domain.repositorios.RepositorioCaracteristicas;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class PublicacionInteresAdopcionTest extends Fixture {
//  private Fixture fixture;
//  private Duenio interesadoAdoptar;
//  private RepositorioCaracteristicas repositorioCaracteristicas;
//  private PublicacionInteresAdopcion publicacionInteresAdopcion;
//
//  @BeforeEach
//  public void iniciar() {
//    this.interesadoAdoptar = adoptante();
//    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
//        estaCastrada,
//        contextura,
//        datosDeInteres,
//        comportamientoConNinios
//    ));
//  }
//
//  @Test
//  public void TestNoPuedoCrearLaPublicacionInteresAdopcionSinResponderPreguntasObligatorias() {
//    Assertions.assertThrows(PreguntaObligatoriaNoContestadaException.class, () -> {
//      new PublicacionInteresAdopcion(
//          interesadoAdoptar,
//          preguntas()
//      );
//    });
//  }
//
//  @Test
//  public void TestPuedoCrearPublicacionInteresAdopcion() {
//    publicacionInteresAdopcion = new PublicacionInteresAdopcion(
//        interesadoAdoptar,
//        preguntasRespondidas()
//    );
//    Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
//  }
//
//  @Test
//  public void TestUnaVezCreadaLaPublicacionInteresAdopcionPuedoDarlaDeBaja() {
//    publicacionInteresAdopcion = new PublicacionInteresAdopcion(
//        interesadoAdoptar,
//        this.preguntasRespondidas()
//    );
//    Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
//    publicacionInteresAdopcion.anularPublicacion();
//    Assertions.assertFalse(publicacionInteresAdopcion.getEstaActiva());
//  }
//
//  private List<SeleccionInteresAdopcion> preguntasRespondidas() {
//    SeleccionInteresAdopcion respuestaTamanio = new SeleccionInteresAdopcion(
//        tamanio,
//        Arrays.asList(tamanioGrande),
//        AlcanceRespuesta.PUBLICACION_INTERES_ADOPCION_COMODIDAD
//    );
//
//    SeleccionInteresAdopcion respuestaComportamiento = new SeleccionInteresAdopcion(
//        comportamientoConNinios,
//        Arrays.asList(comportamientoTranquilo),
//        AlcanceRespuesta.PUBLICACION_INTERES_ADOPCION_COMODIDAD
//    );
//
//    return Arrays.asList(respuestaTamanio, respuestaComportamiento);
//  }
//
//  private List<SeleccionInteresAdopcion> preguntas() {
//    return Stream.concat(this.preguntasRespondidas().stream(),
//                         Stream.of(fixture.preguntaContextura())).collect(Collectors.toList());
//  }
//}