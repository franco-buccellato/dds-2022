package domain.repositorios;

import domain.AlcanceCaracteristica;
import domain.Caracteristica;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  private List<Caracteristica> getCaracteristicasSegun(AlcanceCaracteristica alcanceBuscado) {
    return this.getCaracteristicasDisponibles()
        .stream()
        .filter(
            caracteristica -> caracteristica.getAlcanceCaracteristica()
                .stream()
                .anyMatch(alcanceCaracteristica -> alcanceCaracteristica == alcanceBuscado)
        )
        .collect(Collectors.toList());
  }

  public List<Caracteristica> getCaracteristicasRegistroMascota() {
    return this.getCaracteristicasSegun(AlcanceCaracteristica.REGISTRO_MASCOTA);
  }

  public List<Caracteristica> getCaracteristicasPreguntaAdopcion() {
    return this.getCaracteristicasSegun(AlcanceCaracteristica.PREGUNTA_ADOPCION);
  }

  public List<Caracteristica> getCaracteristicasPreguntaInteresAdopcion() {
    return this.getCaracteristicasSegun(AlcanceCaracteristica.PREGUNTA_INTERES_ADOPCION);
  }
}
