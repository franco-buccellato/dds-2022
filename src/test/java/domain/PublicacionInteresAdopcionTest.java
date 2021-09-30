package domain;

import constants.Fixture;
import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.exception.PreguntasAdopcionSinResponderException;
import domain.repositorios.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PublicacionInteresAdopcionTest {
  private Fixture fixture;
  private Duenio interesadoAdoptar;
  private RepositorioCaracteristicas repositorioCaracteristicas;
  private PublicacionInteresAdopcion publicacionInteresAdopcion;

  @BeforeEach
  public void iniciar() {
    this.fixture = new Fixture();
    this.interesadoAdoptar = fixture.adoptante();
    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
        fixture.estaCastrada(),
        fixture.contextura(),
        fixture.datosDeInteres(),
        fixture.comportamientoConNiños()
    ));
  }

 @Test
 public void TestNoPuedoCrearLaPublicacionInteresAdopcionSinResponderPreguntasObligatorias() {
   Assertions.assertThrows(PreguntaObligatoriaNoContestadaException.class, () -> {
     new PublicacionInteresAdopcion(
         interesadoAdoptar,
         this.preguntas()
     );
   });
 }

 @Test
 public void TestPuedoCrearPublicacionInteresAdopcion() {
   publicacionInteresAdopcion = new PublicacionInteresAdopcion(
       interesadoAdoptar,
       this.preguntasRespondidas()
   );
   Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
 }

 @Test
 public void TestUnaVezCreadaLaPublicacionInteresAdopcionPuedoDarlaDeBaja() {
   publicacionInteresAdopcion = new PublicacionInteresAdopcion(
       interesadoAdoptar,
       this.preguntasRespondidas()
   );
   Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
   publicacionInteresAdopcion.anularPublicacion();
   Assertions.assertFalse(publicacionInteresAdopcion.getEstaActiva());
 }

  private List<Pregunta> preguntasRespondidas() {
    Pregunta tamanio = fixture.preguntaTamanio();
    Pregunta comportamientoConNiños = fixture.preguntaComportamientoConNiños();
    tamanio.seleccionarOpcion(tamanio.getOpciones().get(0), Boolean.TRUE);
    comportamientoConNiños.seleccionarOpcion(comportamientoConNiños.getOpciones().get(0), Boolean.TRUE);
    return Arrays.asList(tamanio, comportamientoConNiños);
  }

  private List<Pregunta> preguntas() {
    return Stream.concat(this.preguntasRespondidas().stream(), Stream.of(fixture.preguntaContextura())).collect(Collectors.toList());
  }
}