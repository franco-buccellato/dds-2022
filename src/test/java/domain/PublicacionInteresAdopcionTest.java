package domain;

import constants.Fixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PublicacionInteresAdopcionTest extends Fixture {
  private Duenio interesadoAdoptar;
  private PublicacionInteresAdopcion publicacionInteresAdopcion;

  @BeforeEach
  public void iniciar()  {
    this.interesadoAdoptar = adoptante();
  }

  @Test
  public void TestPuedoCrearPublicacionInteresAdopcion() {
    publicacionInteresAdopcion = new PublicacionInteresAdopcion(
        interesadoAdoptar,
        preguntasRespondidas()
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

  private List<RespuestaInteresAdopcion> preguntasRespondidas() {
    RespuestaInteresAdopcion respuestaTamanio = new RespuestaInteresAdopcion(
        tamanio,
        Arrays.asList(tamanioGrande)
    );

    RespuestaInteresAdopcion respuestaComportamiento = new RespuestaInteresAdopcion(
        comportamientoConNinios,
        Arrays.asList(comportamientoAmistoso)
    );

    return Arrays.asList(respuestaTamanio, respuestaComportamiento);
  }
}