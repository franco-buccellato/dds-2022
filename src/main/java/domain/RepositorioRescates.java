package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioRescates {
  private List<RescateSinChapa> mascotasEncontradas;
  private static RepositorioRescates repositorioRescate;

  private RepositorioRescates() {
  }

  public static RepositorioRescates getRepositorio() {
    if (repositorioRescate == null) {
      repositorioRescate = new RepositorioRescates();
    }
    return repositorioRescate;
  }

  private List<RescateSinChapa> rescatesEntre(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.mascotasEncontradas
      .stream()
      .filter(mascotaEncontrada -> mascotaEncontrada.encontradaEntre(fechaInicio, fechaFin))
      .collect(Collectors.toList());
  }

  public List<RescateSinChapa> ultimosRescatesEn10Dias() {
    return this.rescatesEntre(LocalDate.now().minusDays(10), LocalDate.now());
  }

  public void setMascotasEncontradas(List<RescateSinChapa> mascotasEncontradas) {
    this.mascotasEncontradas = mascotasEncontradas;
  }
}