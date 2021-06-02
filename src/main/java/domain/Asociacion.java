package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;

public class Asociacion {
  String nombre;
  Ubicacion ubicacion;

  public Asociacion(String nombre, Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    RepositorioAsociaciones.getRepositorioAsociaciones().addAsociacion(this);
  }

  String getNombre() {
    return nombre;
  }

  Ubicacion getUbicacion() {
    return ubicacion;
  }

}
