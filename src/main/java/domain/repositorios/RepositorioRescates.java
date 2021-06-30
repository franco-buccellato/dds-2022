package domain.repositorios;

import domain.Rescate;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioRescates {
  private List<Rescate> rescates;
  private static RepositorioRescates repositorioRescate;

  private RepositorioRescates() {
  }

  public static RepositorioRescates getRepositorio() {
    if (repositorioRescate == null) {
      repositorioRescate = new RepositorioRescates();
    }
    return repositorioRescate;
  }

  private List<Rescate> rescatesEntre(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.rescates
      .stream()
      .filter(rescate -> rescate.ocurrioEntreFechas(fechaInicio, fechaFin))
      .collect(Collectors.toList());
  }

  public List<Rescate> ultimosRescatesEn10Dias() {
    return this.rescatesEntre(LocalDate.now().minusDays(10), LocalDate.now());
  }

  public void setMascotasEncontradas(List<Rescate> rescates) {
    this.rescates = rescates;
  }
}