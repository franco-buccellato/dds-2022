//package domain;
//
//import constants.Fixture;
//import domain.exception.PreguntasAdopcionSinResponderException;
//import domain.repositorios.RepositorioCaracteristicas;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class PublicacionAdopcionTest extends Fixture {
//  private Duenio duenioMascostaEnAdopcion;
//  private Duenio interesadoAdoptar;
//  private Mascota mascotaEnAdopcion;
//  private Asociacion asociacionVinculada;
//  private Asociacion asociacionSinPreguntas;
//  private Asociacion asociacionConPreguntas;
//  private RepositorioCaracteristicas repositorioCaracteristicas;
//
//  @BeforeEach
//  public void iniciar() {
//    this.mascotaEnAdopcion = mascota1();
//    this.duenioMascostaEnAdopcion = duenio();
//    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
//    this.interesadoAdoptar = adoptante();
//    this.asociacionVinculada = asociacion();
//    this.asociacionSinPreguntas = asociacion();
//    this.asociacionConPreguntas = asociacionConPreguntasAdopcion();
//    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
//        estaCastrada(),
//        contextura(),
//        datosDeInteres(),
//        comportamientoConNinios()
//    ));
//  }
//
//  @Test
//  public void TestNoPuedoCrearPublicacionAdopcionPorNoResponderPreguntas() {
//    Assertions.assertThrows(PreguntasAdopcionSinResponderException.class, () -> {
//      new PublicacionAdopcion(
//          duenioMascostaEnAdopcion,
//          mascotaEnAdopcion,
//          asociacionSinPreguntas,
//          this.preguntasAdopcion(asociacionConPreguntas)
//      );
//    });
//  }
//
//  @Test
//  public void TestPuedoCrearPublicacionAdopcion() {
//    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(
//        duenioMascostaEnAdopcion,
//        mascotaEnAdopcion,
//        asociacionSinPreguntas,
//        this.preguntasRespondidas()
//    );
//    Assertions.assertEquals(mascotaEnAdopcion, publicacionAdopcion.getMascota());
//  }
//
//  public List<Pregunta> preguntasRespondidas() {
//    Pregunta contextura = preguntaContextura();
//    Pregunta comportamientoConNiños = preguntaComportamientoConNinios();
//    Pregunta vacunas = preguntaVacunas();
//    Pregunta estaCastrada = preguntaEstaCastrada();
//    contextura.seleccionarOpcion(contextura.getOpciones().get(0), Boolean.TRUE);
//    comportamientoConNiños.seleccionarOpcion(comportamientoConNiños.getOpciones().get(0), Boolean.TRUE);
//    vacunas.seleccionarOpcion(vacunas.getOpciones().get(0), Boolean.TRUE);
//    estaCastrada.seleccionarOpcion(estaCastrada.getOpciones().get(0), Boolean.TRUE);
//    return Arrays.asList(contextura, comportamientoConNiños, vacunas, estaCastrada);
//  }
//
//  private List<Pregunta> preguntasAdopcion(Asociacion asociacionVinculada) {
//    return asociacionVinculada.getPreguntasAdopcion();
//  }
//}