package domain.repositorios;

import domain.Asociacion;
import domain.Ubicacion;
import domain.exception.AsociacionNoEncontradaException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositorioAsociaciones {
  private List<Asociacion> asociaciones = new ArrayList<>();
  private static RepositorioAsociaciones repositorioAsociaciones;

  public static RepositorioAsociaciones getRepositorioAsociaciones() {
    if (repositorioAsociaciones == null) {
      repositorioAsociaciones = new RepositorioAsociaciones();
    }
    return repositorioAsociaciones;
  }

  public List<Asociacion> getAsociaciones() {
    return asociaciones;
  }

  public void setAsociaciones(List<Asociacion> asociaciones) {
    this.asociaciones = asociaciones;
  }

  public void addAsociacion(Asociacion asociacion) {
    asociaciones.add(asociacion);
  }

  public Asociacion encontrarMasCercana(Ubicacion ubicacionOrigen) {
    Optional<Asociacion> asociacion = asociaciones.stream().min(Comparator.comparingDouble(
        unaAsociacion -> unaAsociacion.getUbicacion().distanciaA(ubicacionOrigen)
    ));

    if (!asociacion.isPresent()) {
      throw new AsociacionNoEncontradaException("No se encontro asociacion cercana");
    }

    return asociacion.get();
  }
}
