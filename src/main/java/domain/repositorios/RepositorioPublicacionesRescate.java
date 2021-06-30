package domain.repositorios;

import domain.EstadoPublicacion;
import domain.PublicacionRescate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPublicacionesRescate {
  private List<PublicacionRescate> publicaciones = new ArrayList<>();
  private static RepositorioPublicacionesRescate repositorioPublicacionesRescate;

  public static RepositorioPublicacionesRescate getRepositorioPublicaciones() {
    if (repositorioPublicacionesRescate == null) {
      repositorioPublicacionesRescate = new RepositorioPublicacionesRescate();
    }
    return repositorioPublicacionesRescate;
  }

  public void agregarPublicacion(PublicacionRescate publicacionRescate) {
    publicaciones.add(publicacionRescate);
  }

  public void setPublicaciones(List<PublicacionRescate> publicaciones) {
    this.publicaciones = publicaciones;
  }

  public List<PublicacionRescate> getEnEspera() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.ESPERA)
        .collect(Collectors.toList());
  }

  public List<PublicacionRescate> getAceptadas() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.ACEPTADA)
        .collect(Collectors.toList());
  }

  public List<PublicacionRescate> getRechazadas() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.RECHAZADA)
        .collect(Collectors.toList());
  }

  public List<PublicacionRescate> getPublicaciones() {
    return this.publicaciones;
  }
}
