package domain;

import constants.Fixture;
import domain.exception.PreguntasAdopcionSinResponderException;
import domain.repositorios.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PublicacionAdopcionTest {
  private Fixture fixture;
  private Duenio duenioMascostaEnAdopcion;
  private Duenio interesadoAdoptar;
  private Mascota mascotaEnAdopcion;
  private Asociacion asociacionSinPreguntas;
  private Asociacion asociacionConPreguntas;
  private List<Caracteristica> preguntasAsociacion;
  private RepositorioCaracteristicas repositorioCaracteristicas;
  private PublicacionAdopcion publicacionAdopcion;

  @BeforeEach
  public void iniciar() {
    this.fixture = new Fixture();
    this.mascotaEnAdopcion = fixture.mascota1();
    this.duenioMascostaEnAdopcion = fixture.duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
    this.interesadoAdoptar = fixture.adoptante();
    this.asociacionSinPreguntas = fixture.asociacion();
    this.asociacionConPreguntas = fixture.asociacionConPreguntasAdopcion();
    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
        fixture.estaCastrada(),
        fixture.contextura(),
        fixture.datosDeInteres(),
        fixture.comportamientoConNiños()
    ));
  }

  @Test
  public void TestNoPuedoCrearPublicacionAdopcionPorNoResponderPreguntas() {
    Assertions.assertThrows(PreguntasAdopcionSinResponderException.class, () -> {
      new PublicacionAdopcion(
          duenioMascostaEnAdopcion,
          mascotaEnAdopcion,
          asociacionSinPreguntas,
          this.preguntasAdopcion(repositorioCaracteristicas, asociacionConPreguntas)
      );
    });
  }

  @Test
  public void TestPuedoCrearPublicacionAdopcion() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(
        duenioMascostaEnAdopcion,
        mascotaEnAdopcion,
        asociacionSinPreguntas,
        this.preguntasRespondidas()
    );
    Assertions.assertEquals(mascotaEnAdopcion, publicacionAdopcion.getMascota());
  }

  public List<Caracteristica> preguntasRespondidas() {
    CaracteristicaChoice contextura = fixture.contextura();
    CaracteristicaChoice comportamientoConNiños = fixture.comportamientoConNiños();
    CaracteristicaChoice vacunas = fixture.vacunas();
    CaracteristicaChoice estaCastrada = fixture.estaCastrada();
    contextura.seleccionarOpcion(contextura.getOpciones().get(0), Boolean.TRUE);
    comportamientoConNiños.seleccionarOpcion(comportamientoConNiños.getOpciones().get(0), Boolean.TRUE);
    vacunas.seleccionarOpcion(vacunas.getOpciones().get(0), Boolean.TRUE);
    estaCastrada.seleccionarOpcion(estaCastrada.getOpciones().get(0), Boolean.TRUE);
    return Arrays.asList(contextura, comportamientoConNiños, vacunas, estaCastrada);
  }

  private List<Caracteristica> preguntasAdopcion(RepositorioCaracteristicas repositorioCaracteristicas, Asociacion asociacionVinculada) {
    return Stream.concat(
        repositorioCaracteristicas.getCaracteristicasPreguntaAdopcion().stream(),
        asociacionVinculada.getPreguntasAdopcion().stream())
        .collect(Collectors.toList());
  }
}