package domain.repositorios;

import domain.ObjetivoPregunta;
import domain.Pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioCaracteristicas {
  private List<Pregunta> caracteristicasDisponibles;

  public RepositorioCaracteristicas(List<Pregunta> caracteristicas) {
    this.caracteristicasDisponibles = new ArrayList<>(caracteristicas);
  }

  public List<Pregunta> getCaracteristicasDisponibles() {
    return caracteristicasDisponibles.stream()
        .filter(caracteristica -> caracteristica.getObjetivos().contains(
            ObjetivoPregunta.CARACTERISTICA_MASCOTA)
        ).collect(Collectors.toList());
  }

  public void addCaracteristicasDisponibles(Pregunta caracteristica) {
    this.caracteristicasDisponibles.add(caracteristica);
  }

  public void removeCaracteristicasDisponibles(Pregunta caracteristica) {
    this.caracteristicasDisponibles.remove(caracteristica);
  }
}
