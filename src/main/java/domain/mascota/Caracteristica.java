package domain.mascota;

import static constants.Mensajes.NOT_NULO;

import java.util.Objects;

public class Caracteristica {
  private final TipoCaracteristica tipoCaracteristica;
  private final String descripcion;

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion) {
    this.tipoCaracteristica = Objects.requireNonNull(tipoCaracteristica, NOT_NULO.mensaje("tipoCaracteristica"));
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }
}
