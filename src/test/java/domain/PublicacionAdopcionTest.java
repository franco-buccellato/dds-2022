package domain;

import constants.Fixture;
import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.repositorios.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    this.mascotaEnAdopcion = mascota1();
    this.duenioMascostaEnAdopcion = duenio();
    this.duenioMascostaEnAdopcion.addMascota(mascotaEnAdopcion);
    this.interesadoAdoptar = adoptante();
    this.asociacionVinculada = asociacion();
    this.asociacionSinPreguntas = asociacion();
    this.asociacionConPreguntas = asociacionConPreguntasAdopcion();
    this.repositorioCaracteristicas = new RepositorioCaracteristicas(Arrays.asList(
        estaCastrada(),
        contextura(),
        datosDeInteres(),
        comportamientoConNinios()
    ));
  }

  @Test
  public void TestNoPuedoCrearPublicacionAdopcionPorNoResponderPreguntas() {
    Assertions.assertThrows(PreguntaObligatoriaNoContestadaException.class, () -> {
      new PublicacionAdopcion(
          duenioMascostaEnAdopcion,
          mascotaEnAdopcion,
          asociacionConPreguntas,
          Collections.emptyList()
      );
    });
  }

  @Test
  public void TestPuedoCrearPublicacionAdopcion() {
    List<RespuestaPublicacionAdopcion> respuestas = preguntasRespondidas(asociacionConPreguntas);

    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(
        duenioMascostaEnAdopcion,
        mascotaEnAdopcion,
        asociacionConPreguntas,
        respuestas
    );
    Assertions.assertEquals(mascotaEnAdopcion, publicacionAdopcion.getMascota());
  }

  public List<RespuestaPublicacionAdopcion> preguntasRespondidas(Asociacion asociacion) {
    Pregunta comportamiento = preguntaComportamiento();
    RespuestaPublicacionAdopcion RespuestaComportamiento = new RespuestaPublicacionAdopcion(comportamiento, Collections.singletonList(comportamiento.getOpciones().get(0)));
    Pregunta pregunta1 = asociacion.getPreguntasAdopcion().get(0);
    RespuestaPublicacionAdopcion Respuesta1 = new RespuestaPublicacionAdopcion(pregunta1, Collections.singletonList(pregunta1.getOpciones().get(0)));
    Pregunta pregunta2 = asociacion.getPreguntasAdopcion().get(1);
    RespuestaPublicacionAdopcion Respuesta2 = new RespuestaPublicacionAdopcion(pregunta2, Collections.singletonList(pregunta2.getOpciones().get(0)));

    return Arrays.asList(RespuestaComportamiento, Respuesta1, Respuesta2);
  }

  private List<Pregunta> preguntasAdopcion(Asociacion asociacionVinculada) {
    return asociacionVinculada.getPreguntasAdopcion();
  }
}