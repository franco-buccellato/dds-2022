package domain;

import constants.Fixture;
import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.exception.PreguntasAdopcionSinResponderException;
import domain.repositorios.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PublicacionInteresAdopcionTest {
  private Fixture fixture;
  private Duenio duenioMascostaEnAdopcion;
  private Duenio interesadoAdoptar;
  private Mascota mascotaEnAdopcion;
  private RepositorioCaracteristicas repositorioCaracteristicas;
  private PublicacionInteresAdopcion publicacionInteresAdopcion;

  @BeforeEach
  public void iniciar() {
    this.fixture = new Fixture();
    this.mascotaEnAdopcion = fixture.mascota1();
    this.duenioMascostaEnAdopcion = fixture.duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
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
          this.preguntasInteresAdopcion(repositorioCaracteristicas)
      );
    });

  }

  @Test
  public void TestPuedoCrearPublicacionInteresAdopcion() {
    publicacionInteresAdopcion = new PublicacionInteresAdopcion(
        duenioMascostaEnAdopcion,
        this.preguntasRespondidas()
    );
    Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
  }

  @Test
  public void TestUnaVezCreadaLaPublicacionInteresAdopcionPuedoDarlaDeBaja() {
    publicacionInteresAdopcion = new PublicacionInteresAdopcion(
        duenioMascostaEnAdopcion,
        this.preguntasRespondidas()
    );
    Assertions.assertTrue(publicacionInteresAdopcion.getEstaActiva());
    publicacionInteresAdopcion.anularPublicacion();
    Assertions.assertFalse(publicacionInteresAdopcion.getEstaActiva());
  }

  public List<Caracteristica> preguntasRespondidas() {
    CaracteristicaChoice tamanio = fixture.tamanio();
    CaracteristicaChoice comportamientoConNiños = fixture.comportamientoConNiños();
    tamanio.seleccionarOpcion(tamanio.getOpciones().get(0), Boolean.TRUE);
    comportamientoConNiños.seleccionarOpcion(comportamientoConNiños.getOpciones().get(0), Boolean.TRUE);
    return Arrays.asList(tamanio, comportamientoConNiños);
  }

  private List<Caracteristica> preguntasInteresAdopcion(RepositorioCaracteristicas repositorioCaracteristicas) {
    return repositorioCaracteristicas.getCaracteristicasPreguntaInteresAdopcion();
  }

}