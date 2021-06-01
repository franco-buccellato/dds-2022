package domain;

import java.util.List;

public class RepositorioAsociaciones {

  private List<Asociacion> asociaciones;
  private static RepositorioAsociaciones repositorioAsociaciones;

  public static RepositorioAsociaciones getRrepositorioAsociaciones() {
    if (repositorioAsociaciones == null) {
      repositorioAsociaciones = new RepositorioAsociaciones();
    }
    return repositorioAsociaciones;
  }

  public void setPublicaciones(List<Asociacion> asociaciones) {
    this.asociaciones = asociaciones;
  }

  public Asociacion encontrarMasCercana(Ubicacion ubicacionOigen) {
    int indice = 0;
    int posicionMenor = 0;
    float menorDistancia = 0;
    Asociacion asociacionMasCercana = asociaciones.get(indice), unaAsociacion;
    while (asociaciones.get(indice) != null) {
      unaAsociacion = asociaciones.get(indice);
      if (unaAsociacion.ubicacion.distanciaA(ubicacionOigen) < menorDistancia) {
        menorDistancia = unaAsociacion.ubicacion.distanciaA(ubicacionOigen);
        asociacionMasCercana = unaAsociacion;
        indice++;
      }
      indice++;
    }
    return asociacionMasCercana;
  }
}
