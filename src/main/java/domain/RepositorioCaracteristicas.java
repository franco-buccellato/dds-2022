package repositorios;

import domain.Caracteristica;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracteristicas {
  private List<Caracteristica> caracteristicasDisponibles;

  public RepositorioCaracteristicas(List<Caracteristica> caracteristicas) {
    this.caracteristicasDisponibles = new ArrayList<>(caracteristicas);
  }

  public List<Caracteristica> getCaracteristicasDisponibles() {
    return caracteristicasDisponibles;
  }

  public void addCaracteristicasDisponibles(Caracteristica caracteristica) {
    this.caracteristicasDisponibles.add(caracteristica);
  }

  public void removeCaracteristicasDisponibles(Caracteristica caracteristica) {
    this.caracteristicasDisponibles.remove(caracteristica);
  }
}
