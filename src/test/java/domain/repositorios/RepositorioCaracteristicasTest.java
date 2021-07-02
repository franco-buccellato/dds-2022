package domain.repositorios;

import constants.Fixture;
import domain.Caracteristica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioCaracteristicasTest extends Fixture {
  private RepositorioCaracteristicas repositorio;
  private Caracteristica estaCastrada;
  private Caracteristica datosDeInteres;
  private Caracteristica vacunas;
  private Caracteristica contextura;

  @BeforeEach
  private void setup() {
    estaCastrada = estaCastrada();
    datosDeInteres = datosDeInteres();
    vacunas = vacunas();
    contextura = contextura();
    repositorio = new RepositorioCaracteristicas(Arrays.asList(estaCastrada, datosDeInteres, vacunas));
  }

  @Test
  public void puedoAgregarCaracterisiticasDisponibles() {
    assertEquals(3, repositorio.getCaracteristicasDisponibles().size());

    repositorio.addCaracteristicasDisponibles(comportamientoConNiños());

    assertEquals(4, repositorio.getCaracteristicasDisponibles().size());
  }
  
  @Test
  public void puedoQuitarCaracterisiticasDisponibles() {
    repositorio.removeCaracteristicasDisponibles(repositorio.getCaracteristicasDisponibles().get(0));
    assertEquals(2, repositorio.getCaracteristicasDisponibles().size());
  }

  @Test
  public void TestObtenerCaracteristicasRegistroMascota() {
    Assertions.assertEquals(Arrays.asList(vacunas), repositorio.getCaracteristicasRegistroMascota());
  }

  @Test
  public void TestObtenerCaracteristicasPreguntasAdopcion() {
    repositorio.addCaracteristicasDisponibles(contextura);
    Assertions.assertEquals(Arrays.asList(contextura), repositorio.getCaracteristicasPreguntaAdopcion());
  }
}
