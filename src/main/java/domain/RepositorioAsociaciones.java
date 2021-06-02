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

  public void setAsociaciones(List<Asociacion> asociaciones) {
    this.asociaciones = asociaciones;
  }

  public Asociacion encontrarMasCercana(Ubicacion ubicacionOigen) {
    int indice = asociaciones.size()-1;
    Asociacion asociacionMasCercana = asociaciones.get(indice), unaAsociacion;
    double menorDistancia = asociacionMasCercana.ubicacion.distanciaA(ubicacionOigen);
    indice--;
    while (indice >= 0) {
      unaAsociacion = asociaciones.get(indice);
      if (unaAsociacion.ubicacion.distanciaA(ubicacionOigen) < menorDistancia) {
        menorDistancia = unaAsociacion.ubicacion.distanciaA(ubicacionOigen);
        asociacionMasCercana = unaAsociacion;
        indice--;
      }
      indice--;
    }
    return asociacionMasCercana;
  }

  public int size(){
    return asociaciones.size();
  }

}
