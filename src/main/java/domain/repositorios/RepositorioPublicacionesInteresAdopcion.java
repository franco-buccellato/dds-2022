package domain.repositorios;

import domain.PublicacionAdopcion;
import domain.PublicacionInteresAdopcion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioPublicacionesInteresAdopcion {
  private List<PublicacionInteresAdopcion> publicacionesInteresAdopcion = new ArrayList<>();
  private static RepositorioPublicacionesInteresAdopcion repositorioPublicaciones;

  public static RepositorioPublicacionesInteresAdopcion getRepositorioPublicaciones() {
    if (repositorioPublicaciones == null) {
      repositorioPublicaciones = new RepositorioPublicacionesInteresAdopcion();
    }
    return repositorioPublicaciones;
  }

  public void agregarPublicacion(PublicacionInteresAdopcion publicacion) {
    publicacionesInteresAdopcion.add(publicacion);
  }

  public void setPublicaciones(List<PublicacionInteresAdopcion> publicaciones) {
    this.publicacionesInteresAdopcion = publicaciones;
  }

  public List<PublicacionInteresAdopcion> getPublicaciones() {
    return this.publicacionesInteresAdopcion;
  }

  public void enviarSugerenciasAdopcion(RepositorioPublicacionesAdopcion publicacionAdopcion) {

  }

  private List<PublicacionAdopcion> getSugerenciasAdopcionPara(PublicacionInteresAdopcion publicacionInteresAdopcion) {
    // TODO: Temp para evitar conflictos de buildeo
    return Collections.emptyList();
  }
}
