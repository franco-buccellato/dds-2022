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

  public List<SeleccionPublicacionAdopcion> preguntasRespondidas() {
    SeleccionPublicacionAdopcion contexturaSeleccionada = new SeleccionPublicacionAdopcion(
        contextura,
        Arrays.asList(contexturas.get(0))
    );

    SeleccionPublicacionAdopcion comportamientoSeleccionado = new SeleccionPublicacionAdopcion(
        comportamientoConNinios,
        Arrays.asList(comportamientos.get(0))
    );

    SeleccionPublicacionAdopcion vacunasSeleccionadas = new SeleccionPublicacionAdopcion(
        vacunas,
        opcionesVacunas.stream().limit(2).collect(Collectors.toList())
    );

    SeleccionPublicacionAdopcion estaCastradaSeleccionada = new SeleccionPublicacionAdopcion(
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

  private List<SeleccionPublicacionAdopcion> preguntasAdopcion() {

    return Arrays.asList(new SeleccionPublicacionAdopcion(
        estaCastrada,
        Arrays.asList(estaCastrada.getOpciones().get(0))
    ));
  }
}