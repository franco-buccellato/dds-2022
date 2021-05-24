package domain;

import domain.mascota.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static domain.mascota.TipoCaracteristica.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioCaracterisiticasTest {
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

  protected CaracteristicaBooleana estaCastrada() {
    return new CaracteristicaBooleana("Esta Castrada:", false);
  }
  protected CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", false);
  }
  protected CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, true);
  }
  protected CaracteristicaChoice comportamientoConNiños() {
    Opcion jugueton = new Opcion("Jugueton");
    Opcion agresivo = new Opcion("Agresivo");
    Opcion desinteres = new Opcion("Desinteres");
    List<Opcion> comportamientos = Arrays.asList(jugueton, agresivo, desinteres);

    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, true);
  }
}
