package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Fixture;
import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.repositorios.RepositorioCaracteristicas;

public class PublicacionAdopcionTest extends Fixture {
  private Duenio duenioMascostaEnAdopcion;
  private Duenio interesadoAdoptar;
  private Mascota mascotaEnAdopcion;
  private Asociacion asociacionVinculada;
  private Asociacion asociacionSinPreguntas;
  private Asociacion asociacionConPreguntas;
  private RepositorioCaracteristicas repositorioCaracteristicas;

  @BeforeEach
  public void iniciar() {
    super.generalSetup();
    this.mascotaEnAdopcion = mascota1();
    this.duenioMascostaEnAdopcion = duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
    this.interesadoAdoptar = adoptante();
    this.asociacionVinculada = asociacion();
    this.asociacionSinPreguntas = asociacion();
    this.asociacionConPreguntas = asociacionConPreguntasAdopcion();
    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
        estaCastrada,
        contextura,
        datosDeInteres,
        comportamientoConNinios
    ));
  }

  @Test
  public void TestNoPuedoCrearPublicacionAdopcionPorNoResponderPreguntas() {
    Assertions.assertThrows(PreguntaObligatoriaNoContestadaException.class, () -> {
      new PublicacionAdopcion(
          duenioMascostaEnAdopcion,
          mascotaEnAdopcion,
          asociacionConPreguntas,
          this.preguntasAdopcion()
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

  public List<RespuestaPublicacionAdopcion> preguntasRespondidas() {
    RespuestaPublicacionAdopcion contexturaSeleccionada = new RespuestaPublicacionAdopcion(
        contextura,
        Arrays.asList(contexturas.get(0))
    );

    RespuestaPublicacionAdopcion comportamientoSeleccionado = new RespuestaPublicacionAdopcion(
        comportamientoConNinios,
        Arrays.asList(comportamientos.get(0))
    );

    RespuestaPublicacionAdopcion vacunasSeleccionadas = new RespuestaPublicacionAdopcion(
        vacunas,
        opcionesVacunas.stream().limit(2).collect(Collectors.toList())
    );

    RespuestaPublicacionAdopcion estaCastradaSeleccionada = new RespuestaPublicacionAdopcion(
        estaCastrada,
        Arrays.asList(opcionesBool.get(0))
    );

    return Arrays.asList(
        contexturaSeleccionada,
        comportamientoSeleccionado,
        vacunasSeleccionadas,
        estaCastradaSeleccionada
    );
  }

  private List<RespuestaPublicacionAdopcion> preguntasAdopcion() {

    return Arrays.asList(new RespuestaPublicacionAdopcion(
        estaCastrada,
        Arrays.asList(estaCastrada.getOpciones().get(0))
    ));
  }
}