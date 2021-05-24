package domain.mascota;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;

public class TipoCaracteristica {
  private final String descripcion;

  public TipoCaracteristica(String descripcion) {
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
  }

  public String getDescripcion() {
    return descripcion;
  }
}
