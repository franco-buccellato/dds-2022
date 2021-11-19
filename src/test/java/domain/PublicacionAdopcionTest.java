package domain;

import constants.Fixture;
import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.repositorios.RepositorioPreguntas;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PublicacionAdopcionTest extends Fixture {
  private Duenio duenioMascostaEnAdopcion;
  private Mascota mascotaEnAdopcion;
  private Asociacion asociacionSinPreguntas;
  private Asociacion asociacionConPreguntas;

  @BeforeEach
  public void iniciar() {
    super.generalSetup();
    this.mascotaEnAdopcion = mascota1();
    this.duenioMascostaEnAdopcion = duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
    Duenio interesadoAdoptar = adoptante();
    Asociacion asociacionVinculada = asociacion();
    this.asociacionSinPreguntas = asociacion();
    this.asociacionConPreguntas = asociacionConPreguntasAdopcion();
    RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas(Arrays.asList(
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
        Arrays.asList(comportamientosConNinios.get(0))
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