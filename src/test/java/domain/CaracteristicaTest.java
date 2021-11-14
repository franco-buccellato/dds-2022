package domain;

import constants.Fixture;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CaracteristicaTest extends Fixture {

  @Test
  public void puedoObtenerSiUnaRespuestaEsValida() {
    CaracteristicaChoice estaCastrada = estaCastrada();
    List<Opcion> respuestaValidaEstaCastrada = Collections.singletonList(si);
    List<Opcion> respuestaInvalidaEstaCastrada = Collections.singletonList(tipoGato);

    assertTrue(estaCastrada.esRespuestaValida(respuestaValidaEstaCastrada));
    assertFalse(estaCastrada.esRespuestaValida(respuestaInvalidaEstaCastrada));
  }

  @Test
  public void puedoVerificarTienenMismasOpciones() {
    CaracteristicaChoice caracteristicaEstaCastrada = estaCastrada();
    Pregunta preguntaEstaCastrada = preguntaEstaCastrada();
    Pregunta preguntaTamanio = preguntaTamanio();

    assertTrue(caracteristicaEstaCastrada.tieneMismasOpciones(preguntaEstaCastrada));
    assertFalse(caracteristicaEstaCastrada.tieneMismasOpciones(preguntaTamanio));
  }
}
