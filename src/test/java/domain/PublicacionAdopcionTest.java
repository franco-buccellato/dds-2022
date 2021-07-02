package domain;

import constants.Fixture;
import domain.exception.PreguntasAdopcionSinResponderException;
import domain.repositorios.RepositorioAsociaciones;
import domain.repositorios.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PublicacionAdopcionTest extends Fixture {
  private Duenio duenioMascostaEnAdopcion;
  private Duenio interesadoAdoptar;
  private Mascota mascotaEnAdopcion;
  private Asociacion asociacionVinculada;
  private List<Caracteristica> preguntasAsociacion;
  private RepositorioCaracteristicas repositorioCaracteristicas;
  private PublicacionAdopcion publicacionAdopcion;

  @BeforeEach
  public void iniciar() {
    this.mascotaEnAdopcion = mascota1();
    this.duenioMascostaEnAdopcion = duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
    this.interesadoAdoptar = adoptante();
    this.asociacionVinculada = asociacion();
    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
        estaCastrada(),
        contextura(),
        datosDeInteres(),
        comportamientoConNiños()
    ));
  }
  @Test
  public void TestNoPuedoCrearPublicacionAdopcionPorNoResponderPreguntas() {
    List<Caracteristica> preguntasAsociacion =
        Stream.concat(
            repositorioCaracteristicas.getCaracteristicasPreguntaAdopcion().stream(),
            asociacionVinculada.getPreguntasAdopcion().stream())
            .collect(Collectors.toList());
    Assertions.assertThrows(PreguntasAdopcionSinResponderException.class, () -> {new PublicacionAdopcion(
        duenioMascostaEnAdopcion,
        mascotaEnAdopcion,
        asociacionVinculada,
        preguntasAsociacion
        );});
  }

  @Test
  public void TestPuedoCrearPublicacionAdopcion() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(
        duenioMascostaEnAdopcion,
        mascotaEnAdopcion,
        asociacionVinculada,
        this.preguntasRespondidas()
    );
    Assertions.assertEquals(mascotaEnAdopcion, publicacionAdopcion.getMascota());
  }

  public List<Caracteristica> preguntasRespondidas() {
    CaracteristicaChoice contextura = contextura();
    CaracteristicaChoice comportamientoConNiños = comportamientoConNiños();
    CaracteristicaInput datosDeInteres = datosDeInteres();
    contextura.seleccionarOpcion((Opcion) contextura.getOpciones().get(0), Boolean.TRUE);
    comportamientoConNiños.seleccionarOpcion((Opcion) comportamientoConNiños.getOpciones().get(0), Boolean.TRUE);
    datosDeInteres.setInput("Pierde mucho pelo");
    return Arrays.asList(contextura, comportamientoConNiños, datosDeInteres);
  }
}