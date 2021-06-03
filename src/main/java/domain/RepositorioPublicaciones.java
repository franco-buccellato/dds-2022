package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPublicaciones {
  private List<Publicacion> publicaciones = new ArrayList<>();
  private static RepositorioPublicaciones repositorioPublicaciones;

  public static RepositorioPublicaciones getRepositorioPublicaciones() {
    if (repositorioPublicaciones == null) {
      repositorioPublicaciones = new RepositorioPublicaciones();
    }
    return repositorioPublicaciones;
  }

  public void agregarPublicacion(Publicacion publicacion) {
    publicaciones.add(publicacion);
  }

  public void setPublicaciones(List<Publicacion> publicaciones) {
    this.publicaciones = publicaciones;
  }

  List<Publicacion> getEnEspera() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.ESPERA)
        .collect(Collectors.toList());
  }

  List<Publicacion> getAceptadas() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.ACEPTADA)
        .collect(Collectors.toList());
  }

  List<Publicacion> getRechazadas() {
    return publicaciones
        .stream()
        .filter(unaPublicacion -> unaPublicacion.getEstado() == EstadoPublicacion.RECHAZADA)
        .collect(Collectors.toList());
  }

  List<Publicacion> getPublicaciones() {
    return this.publicaciones;
  }
}
