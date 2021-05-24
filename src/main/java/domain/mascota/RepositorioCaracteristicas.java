package domain.mascota;

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
    System.out.println(caracteristica.getClass());
    System.out.println(caracteristicasDisponibles.getClass());
    this.caracteristicasDisponibles.add(caracteristica);
  }

  public void removeCaracteristicasDisponibles(Caracteristica caracteristica) {
    this.caracteristicasDisponibles.remove(caracteristica);
  }
}
