package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioMascotaEncontrada {
  private List<RescateSinChapa> mascotasEncontradas;
  private static RepositorioMascotaEncontrada repositorioMascotaEncontrada;

  private RepositorioMascotaEncontrada() {
  }

  public static RepositorioMascotaEncontrada getRepositorio() {
    if (repositorioMascotaEncontrada == null) {
      repositorioMascotaEncontrada = new RepositorioMascotaEncontrada();
    }
    return repositorioMascotaEncontrada;
  }

  private List<RescateSinChapa> mascotasEncontradasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.mascotasEncontradas
        .stream()
        .filter(mascotaEncontrada -> mascotaEncontrada.encontradaEntre(fechaInicio, fechaFin))
        .collect(Collectors.toList());
  }

  public List<RescateSinChapa> ultimasEncontradasEn10Dias() {
    return this.mascotasEncontradasEntre(LocalDate.now().minusDays(10), LocalDate.now());
  }

  public void setMascotasEncontradas(List<RescateSinChapa> mascotasEncontradas) {
    this.mascotasEncontradas = mascotasEncontradas;
  }
}