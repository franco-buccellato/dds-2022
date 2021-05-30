package domain;

import domain.mascota.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioCaracterisiticasTest extends Caracteristicas {
  RepositorioCaracteristicas repositorio;

  @BeforeEach
  private void setup() {
    repositorio = new RepositorioCaracteristicas(Arrays.asList(estaCastrada(), datosDeInteres(), vacunas()));
  }

  @Test
  public void puedoLeerCaracterisiticasDisponibles() {
    assertEquals(3, repositorio.getCaracteristicasDisponibles().size());
  }
  @Test
  public void puedoAgregarCaracterisiticasDisponibles() {
    repositorio.addCaracteristicasDisponibles(comportamientoConNiños());

    assertEquals(4, repositorio.getCaracteristicasDisponibles().size());
  }
  @Test
  public void puedoQuitarCaracterisiticasDisponibles() {
    repositorio.removeCaracteristicasDisponibles(repositorio.getCaracteristicasDisponibles().get(0));
    assertEquals(2, repositorio.getCaracteristicasDisponibles().size());
  }
}
