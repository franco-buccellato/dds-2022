package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import constants.Fixture;
import domain.repositorios.RepositorioPreguntas;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RepositorioPreguntasTest extends Fixture {
  private RepositorioPreguntas repositorio;

  @BeforeEach
  private void setup() {
    repositorio = new RepositorioPreguntas(Arrays.asList(estaCastrada, datosDeInteres, vacunas));
  }

  @Test
  public void puedoAgregarCaracterisiticasDisponibles() {
    assertEquals(3, repositorio.getCaracteristicasDisponibles().size());

    repositorio.addCaracteristicasDisponibles(comportamientoConNinios);

    assertEquals(4, repositorio.getCaracteristicasDisponibles().size());
  }

  @Test
  public void puedoQuitarCaracterisiticasDisponibles() {
    repositorio.removeCaracteristicasDisponibles(repositorio.getCaracteristicasDisponibles().get(0));
    assertEquals(2, repositorio.getCaracteristicasDisponibles().size());
  }
}
