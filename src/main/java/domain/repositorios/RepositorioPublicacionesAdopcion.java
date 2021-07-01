package domain.repositorios;

import domain.PublicacionAdopcion;
import domain.PublicacionInteresAdopcion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPublicacionesAdopcion {
  private List<PublicacionAdopcion> publicacionesAdopcion = new ArrayList<>();
  private static RepositorioPublicacionesAdopcion repositorioPublicaciones;

  public static RepositorioPublicacionesAdopcion getRepositorioPublicaciones() {
    if (repositorioPublicaciones == null) {
      repositorioPublicaciones = new RepositorioPublicacionesAdopcion();
    }
    return repositorioPublicaciones;
  }

  public void agregarPublicacion(PublicacionAdopcion publicacion) {
    publicacionesAdopcion.add(publicacion);
  }

  public void setPublicaciones(List<PublicacionAdopcion> publicaciones) {
    this.publicacionesAdopcion = publicaciones;
  }

  public List<PublicacionAdopcion> getPublicaciones() {
    return this.publicacionesAdopcion;
  }

  public List<PublicacionAdopcion> recomendarAdopcion(PublicacionInteresAdopcion publicacionInteres) {
    return this.publicacionesAdopcion
            .stream()
            .filter(publicacionAdopcion -> publicacionAdopcion.getMascota().cumpleConCaracteristicas(publicacionInteres.getPreguntas()))
            .collect(Collectors.toList());
  }
}
