package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioAsociaciones;
import java.util.Objects;

public class Asociacion {
  private String nombre;
  private Ubicacion ubicacion;

  public Asociacion(String nombre, Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    RepositorioAsociaciones.getRepositorioAsociaciones().addAsociacion(this);
  }

  public String getNombre() {
    return nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

}
